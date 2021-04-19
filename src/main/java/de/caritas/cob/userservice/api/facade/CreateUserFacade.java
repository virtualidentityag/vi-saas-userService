package de.caritas.cob.userservice.api.facade;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import de.caritas.cob.userservice.api.exception.httpresponses.BadRequestException;
import de.caritas.cob.userservice.api.exception.httpresponses.CustomValidationHttpStatusException;
import de.caritas.cob.userservice.api.exception.httpresponses.InternalServerErrorException;
import de.caritas.cob.userservice.api.exception.httpresponses.customheader.HttpStatusExceptionReason;
import de.caritas.cob.userservice.api.facade.rollback.RollbackFacade;
import de.caritas.cob.userservice.api.facade.rollback.RollbackUserAccountInformation;
import de.caritas.cob.userservice.api.helper.AgencyVerifier;
import de.caritas.cob.userservice.api.manager.consultingtype.ConsultingTypeManager;
import de.caritas.cob.userservice.api.manager.consultingtype.ConsultingTypeSettings;
import de.caritas.cob.userservice.api.model.keycloak.KeycloakCreateUserResponseDTO;
import de.caritas.cob.userservice.api.model.registration.UserDTO;
import de.caritas.cob.userservice.api.repository.user.User;
import de.caritas.cob.userservice.api.service.helper.KeycloakAdminClientService;
import de.caritas.cob.userservice.api.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Facade to encapsulate the steps to initialize an user account (create chat/agency relation or a
 * new session).
 */
@Service
@RequiredArgsConstructor
public class CreateUserFacade {

  private final @NonNull KeycloakAdminClientService keycloakAdminClientService;
  private final @NonNull UserService userService;
  private final @NonNull RollbackFacade rollbackFacade;
  private final @NonNull ConsultingTypeManager consultingTypeManager;
  private final @NonNull AgencyVerifier agencyVerifier;
  private final @NonNull CreateNewConsultingTypeFacade createNewConsultingTypeFacade;

  /**
   * Creates a user in Keycloak and MariaDB. Then creates a session or chat account depending on the
   * provided consulting ID.
   *
   * @param userDTO {@link UserDTO}
   */
  public void createUserAndInitializeAccount(final UserDTO userDTO) {

    if (!keycloakAdminClientService.isUsernameAvailable(userDTO.getUsername())) {
      throw new CustomValidationHttpStatusException(
          HttpStatusExceptionReason.USERNAME_NOT_AVAILABLE, HttpStatus.CONFLICT);
    }

    ConsultingTypeSettings consultingTypeSettings =
        consultingTypeManager.getConsultingTypeSettings(userDTO.getConsultingId());
    checkIfConsultingTypeMatchesToAgency(userDTO, consultingTypeSettings.getConsultingId());
    KeycloakCreateUserResponseDTO response = keycloakAdminClientService.createKeycloakUser(userDTO);
    updateKeycloakAccountAndCreateDatabaseUserAccount(response.getUserId(), userDTO,
        consultingTypeSettings.getConsultingId());
  }

  private void checkIfConsultingTypeMatchesToAgency(UserDTO user, int consultingId) {
    if (!agencyVerifier.doesConsultingTypeMatchToAgency(user.getAgencyId(), consultingId)) {
      throw new BadRequestException(String.format("Agency with id %s does not match to consulting"
          + " type %d", user.getAgencyId(), consultingId));
    }
  }

  private void updateKeycloakAccountAndCreateDatabaseUserAccount(String userId, UserDTO userDTO,
      int consultingType) {

    checkIfUserIdNotNull(userId, userDTO);

    ConsultingTypeSettings consultingTypeSettings =
        consultingTypeManager.getConsultingTypeSettings(consultingType);
    User user;

    try {
      // We need to set the user roles and password and (dummy) e-mail address after the user was
      // created in Keycloak
      keycloakAdminClientService.updateUserRole(userId);
      keycloakAdminClientService.updatePassword(userId, userDTO.getPassword());

      user = userService
          .createUser(userId, userDTO.getUsername(), returnDummyEmailIfNoneGiven(userDTO, userId),
              consultingTypeSettings.isLanguageFormal());

    } catch (Exception ex) {
      rollbackFacade
          .rollBackUserAccount(RollbackUserAccountInformation.builder().userId(userId)
              .rollBackUserAccount(Boolean.parseBoolean(userDTO.getTermsAccepted())).build());
      throw new InternalServerErrorException(
          String
              .format("Could not update account data on registration for: %s", userDTO.toString()));
    }

    createNewConsultingTypeFacade
        .initializeNewConsultingType(userDTO, user, consultingTypeSettings);
  }

  private String returnDummyEmailIfNoneGiven(UserDTO userDTO, String userId) {
    if (isBlank(userDTO.getEmail())) {
      return keycloakAdminClientService.updateDummyEmail(userId, userDTO);
    }

    return userDTO.getEmail();
  }

  private void checkIfUserIdNotNull(String userId, UserDTO userDTO) {
    if (isNull(userId)) {
      throw new InternalServerErrorException(
          String.format("Could not create Keycloak account for: %s", userDTO.toString()));
    }
  }

}
