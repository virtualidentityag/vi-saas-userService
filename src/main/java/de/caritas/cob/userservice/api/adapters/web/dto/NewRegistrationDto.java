package de.caritas.cob.userservice.api.adapters.web.dto;

import static de.caritas.cob.userservice.api.helper.UserHelper.AGENCY_ID_MAX;
import static de.caritas.cob.userservice.api.helper.UserHelper.AGENCY_ID_MIN;
import static de.caritas.cob.userservice.api.helper.UserHelper.AGE_REGEXP;
import static de.caritas.cob.userservice.api.helper.UserHelper.CONSULTING_TYPE_REGEXP;
import static de.caritas.cob.userservice.api.helper.UserHelper.REFERER_REGEXP;
import static de.caritas.cob.userservice.api.helper.UserHelper.VALID_POSTCODE_REGEX;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** Model for new consulting type registrations */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "NewRegistration")
@ToString
public class NewRegistrationDto implements UserRegistrationDTO {

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

  @NotBlank(message = "{user.consultingType.invalid}")
  @NotNull(message = "{user.consultingType.invalid}")
  @Pattern(regexp = CONSULTING_TYPE_REGEXP, message = "{user.consultingType.invalid}")
  @Schema(required = true, example = "\"0\"")
  @JsonProperty("consultingType")
  private String consultingType;

  @Schema(hidden = true)
  private boolean newUserAccount;

  private String consultantId;

  @Schema(required = false, example = "\"2\"")
  @JsonProperty("mainTopicId")
  private Long mainTopicId;

  @Schema(required = false, example = "\"MALE\"")
  @JsonProperty("gender")
  private String userGender;

  @Pattern(regexp = AGE_REGEXP, message = "{user.custom.age.invalid}")
  @Schema(example = "1")
  @JsonProperty("age")
  private String age;

  public Integer getUserAge() {
    return age == null ? null : Integer.valueOf(age);
  }

  @Schema(required = false, example = "\"[1,5]\"")
  @JsonProperty("topicIds")
  private Collection<Long> topicIds;

  @Schema(required = false, example = "\"RELATIVE_COUNSELLING\"")
  @JsonProperty("counsellingRelation")
  private String counsellingRelation;

  @Schema(required = false, example = "\"referer\"")
  @Pattern(regexp = REFERER_REGEXP, message = "{user.custom.referer.invalid}")
  @JsonProperty("referer")
  private String referer;
}
