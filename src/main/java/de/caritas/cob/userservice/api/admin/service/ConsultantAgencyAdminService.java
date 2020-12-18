package de.caritas.cob.userservice.api.admin.service;

import de.caritas.cob.userservice.api.admin.service.consultant.create.ConsultantAgencyCreatorService;
import de.caritas.cob.userservice.api.exception.httpresponses.BadRequestException;
import de.caritas.cob.userservice.api.model.ConsultantAgencyAdminResultDTO;
import de.caritas.cob.userservice.api.model.CreateConsultantAgencyDTO;
import de.caritas.cob.userservice.api.repository.consultant.Consultant;
import de.caritas.cob.userservice.api.repository.consultant.ConsultantRepository;
import de.caritas.cob.userservice.api.repository.consultantAgency.ConsultantAgency;
import de.caritas.cob.userservice.api.repository.consultantAgency.ConsultantAgencyRepository;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class to handle administrative operations on consultant-agencies.
 */
@Service
@RequiredArgsConstructor
public class ConsultantAgencyAdminService {

  private final @NonNull ConsultantAgencyRepository consultantAgencyRepository;
  private final @NonNull ConsultantRepository consultantRepository;
  private final @NonNull ConsultantAgencyCreatorService consultantAgencyCreatorService;

  /**
   * Returns all Agencies for the given consultantId.
   *
   * @param consultantId id of the consultant
   * @return the list of agencies for the given consultant
   */
  public ConsultantAgencyAdminResultDTO findConsultantAgencies(String consultantId) {
    Optional<Consultant> consultant = consultantRepository.findById(consultantId);
    if (!consultant.isPresent()) {
      throw new BadRequestException(
          String.format("Consultant with id %s does not exist", consultantId));
    }
    List<ConsultantAgency> agencyList = consultantAgencyRepository
        .findByConsultantId(consultantId);

    return ConsultantAgencyAdminResultDTOBuilder
        .getInstance()
        .withConsultantId(consultantId)
        .withResult(agencyList)
        .build();
  }

  /**
   * Creates a new {@Link ConsultantAgency} based on the consultantId and {@Link
   * CreateConsultantAgencyDTO} input.
   *
   * @param consultantId              the consultant to use
   * @param createConsultantAgencyDTO the agencyId and role
   * @return the generated and persisted {@link ConsultantAgency} representation as {@link
   * ConsultantAgencyAdminResultDTO}
   */
  public ConsultantAgencyAdminResultDTO createNewConsultantAgency(String consultantId,
      CreateConsultantAgencyDTO createConsultantAgencyDTO) {
    return consultantAgencyCreatorService
        .createNewConsultantAgency(consultantId, createConsultantAgencyDTO);
  }
}
