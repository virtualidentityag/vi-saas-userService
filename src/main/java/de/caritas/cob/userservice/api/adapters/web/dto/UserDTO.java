package de.caritas.cob.userservice.api.adapters.web.dto;

import static de.caritas.cob.userservice.api.helper.UserHelper.AGENCY_ID_MAX;
import static de.caritas.cob.userservice.api.helper.UserHelper.AGENCY_ID_MIN;
import static de.caritas.cob.userservice.api.helper.UserHelper.AGE_REGEXP;
import static de.caritas.cob.userservice.api.helper.UserHelper.CONSULTING_TYPE_REGEXP;
import static de.caritas.cob.userservice.api.helper.UserHelper.REFERER_REGEXP;
import static de.caritas.cob.userservice.api.helper.UserHelper.STATE_REGEXP;
import static de.caritas.cob.userservice.api.helper.UserHelper.TERMS_ACCEPTED_REGEXP;
import static de.caritas.cob.userservice.api.helper.UserHelper.VALID_POSTCODE_REGEX;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.Lists;
import de.caritas.cob.userservice.api.adapters.web.dto.serialization.EncodeUsernameJsonDeserializer;
import de.caritas.cob.userservice.api.adapters.web.dto.serialization.UrlDecodePasswordJsonDeserializer;
import de.caritas.cob.userservice.api.adapters.web.dto.validation.ValidAge;
import de.caritas.cob.userservice.api.adapters.web.dto.validation.ValidState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/** User model */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "User")
@ValidAge
@ValidState
@Builder
public class UserDTO implements UserRegistrationDTO {

  @NotBlank(message = "{user.username.notBlank}")
  @NotNull(message = "{user.username.notBlank}")
  @Schema(required = true, example = "max94")
  @JsonDeserialize(using = EncodeUsernameJsonDeserializer.class)
  @JsonProperty("username")
  private String username;

  @NotBlank(message = "{user.custom.postcode.notNull}")
  @NotNull(message = "{user.custom.postcode.notNull}")
  @Pattern(regexp = VALID_POSTCODE_REGEX, message = "{user.custom.postcode.invalid}")
  @Schema(required = true, example = "\"79098\"")
  @JsonProperty("postcode")
  private String postcode;

  @NotNull(message = "{user.custom.agency.notNull}")
  @Min(value = AGENCY_ID_MIN, message = "{user.custom.agency.invalid}")
  @Max(value = AGENCY_ID_MAX, message = "{user.custom.agency.invalid}")
  @Schema(required = true, example = "\"15\"")
  @JsonProperty("agencyId")
  private Long agencyId;

  @NotBlank(message = "{user.password.notBlank}")
  @Schema(required = true, example = "pass@w0rd")
  @JsonDeserialize(using = UrlDecodePasswordJsonDeserializer.class)
  @JsonProperty("password")
  private String password;

  @JsonInclude(value = Include.NON_NULL)
  @Email(message = "{user.email.invalid}")
  @Schema(example = "max@mustermann.de")
  @JsonProperty("email")
  private String email;

  @JsonInclude(value = Include.NON_NULL)
  @Pattern(regexp = AGE_REGEXP, message = "{user.custom.age.invalid}")
  @Schema(example = "1")
  @JsonProperty("age")
  private String age;

  @JsonInclude(value = Include.NON_NULL)
  @Pattern(regexp = STATE_REGEXP, message = "{user.custom.state.invalid}")
  @JsonProperty("state")
  @Schema(example = "\"16\"")
  private String state;

  @Pattern(regexp = TERMS_ACCEPTED_REGEXP, message = "{user.custom.termsAccepted.invalid}")
  @Schema(required = true, example = "\"true\"")
  @JsonProperty("termsAccepted")
  private String termsAccepted;

  @Pattern(regexp = CONSULTING_TYPE_REGEXP, message = "{user.consultingType.invalid}")
  @Schema(required = true, example = "\"0\"")
  @JsonProperty("consultingType")
  private String consultingType;

  @JsonProperty("consultantId")
  private String consultantId;

  private boolean newUserAccount;

  @Schema(required = false, example = "\"1\"")
  @JsonProperty("tenantId")
  private Long tenantId;

  @Schema(required = false, example = "\"2\"")
  @JsonProperty("mainTopicId")
  private Long mainTopicId;

  @Schema(required = false, example = "\"MALE\"")
  @JsonProperty("gender")
  private String userGender;

  @Schema(required = false, example = "\"[1,5]\"")
  @JsonProperty("topicIds")
  private Collection<Long> topicIds = Lists.newArrayList();

  @Schema(required = false, example = "\"RELATIVE_COUNSELLING\"")
  @JsonProperty("counsellingRelation")
  private String counsellingRelation;

  private LanguageCode preferredLanguage;

  @Schema(required = false, example = "\"referer\"")
  @Pattern(regexp = REFERER_REGEXP, message = "{user.custom.referer.invalid}")
  @JsonProperty("referer")
  private String referer;

  public Integer getUserAge() {
    return StringUtils.isNumeric(age) ? Integer.valueOf(age) : null;
  }

  public UserDTO(String email) {
    this.email = email;
  }

  public UserDTO(
      String username,
      String postcode,
      Long agencyId,
      String password,
      String email,
      String termsAccepted,
      String consultingTypeId) {
    this.username = username;
    this.postcode = postcode;
    this.agencyId = agencyId;
    this.password = password;
    this.email = email;
    this.termsAccepted = termsAccepted;
    this.consultingType = consultingTypeId;
  }

  public UserDTO(String age, String state, String consultingType) {
    this.age = age;
    this.state = state;
    this.consultingType = consultingType;
  }

  @JsonIgnore
  public boolean isConsultantSet() {
    return isNotBlank(consultantId);
  }

  @Override
  public String toString() {
    return "UserDTO{"
        + "username='"
        + username
        + '\''
        + ", postcode='"
        + postcode
        + '\''
        + ", agencyId="
        + agencyId
        + ", age='"
        + age
        + '\''
        + ", state='"
        + state
        + '\''
        + ", termsAccepted='"
        + termsAccepted
        + '\''
        + ", consultingType='"
        + consultingType
        + '\''
        + ", tenantId='"
        + tenantId
        + '\''
        + ", mainTopicId='"
        + mainTopicId
        + '\''
        + ", gender='"
        + userGender
        + '\''
        + ", topicIds='"
        + topicIds
        + '\''
        + ", counsellingRelation='"
        + counsellingRelation
        + '\''
        + '}';
  }
}
