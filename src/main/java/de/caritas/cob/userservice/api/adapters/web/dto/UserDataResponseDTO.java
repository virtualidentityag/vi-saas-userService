package de.caritas.cob.userservice.api.adapters.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.caritas.cob.userservice.api.adapters.web.dto.serialization.DecodeUsernameJsonSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(name = "UserData")
public class UserDataResponseDTO {

  @Schema(example = "ajsd89-sdf9-sadk-as8j-asdf8jo")
  private String userId;

  @Schema(example = "max.muster")
  @JsonSerialize(using = DecodeUsernameJsonSerializer.class)
  private String userName;

  @Schema(example = "Max")
  private String firstName;

  @Schema(example = "Mustermann")
  private String lastName;

  @Schema(example = "maxmuster@mann.com")
  private String email;

  @Schema(example = "true")
  private boolean isAbsent;

  @Schema(example = "true")
  private boolean isFormalLanguage;

  @Schema private Set<String> languages;

  @Schema(example = "Bin mal weg...")
  private String absenceMessage;

  @Schema(example = "true")
  private boolean isInTeamAgency;

  @Schema private List<AgencyDTO> agencies;

  @Schema private Set<String> userRoles;

  @Schema private Set<String> grantedAuthorities;

  private LinkedHashMap<String, Object> consultingTypes;
  private boolean hasAnonymousConversations;
  private boolean hasArchive;
  private TwoFactorAuthDTO twoFactorAuth;
  private String displayName;
  private Boolean isDisplayNameEditable;

  @JsonIgnore private Boolean encourage2fa;

  private Boolean e2eEncryptionEnabled;

  private Boolean isWalkThroughEnabled;

  private Set<EmailToggle> emailToggles;

  private Boolean appointmentFeatureEnabled;

  private LanguageCode preferredLanguage;

  private LocalDateTime termsAndConditionsConfirmation;

  private LocalDateTime dataPrivacyConfirmation;

  private Boolean available;

  private EmailNotificationsDTO emailNotifications;
}
