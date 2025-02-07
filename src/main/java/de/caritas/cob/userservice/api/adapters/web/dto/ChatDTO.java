package de.caritas.cob.userservice.api.adapters.web.dto;

import static de.caritas.cob.userservice.api.helper.UserHelper.CHAT_MAX_DURATION;
import static de.caritas.cob.userservice.api.helper.UserHelper.CHAT_MIN_DURATION;
import static de.caritas.cob.userservice.api.helper.UserHelper.CHAT_TOPIC_MAX_LENGTH;
import static de.caritas.cob.userservice.api.helper.UserHelper.CHAT_TOPIC_MIN_LENGTH;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/** Create new chat model */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(name = "Chat")
public class ChatDTO {

  @Size(min = CHAT_TOPIC_MIN_LENGTH, max = CHAT_TOPIC_MAX_LENGTH)
  @NotBlank(message = "{chat.name.notBlank}")
  @Schema(required = true, example = "Wöchentliche Drogenberatung")
  @JsonProperty("topic")
  private String topic;

  @DateTimeFormat(iso = ISO.DATE)
  @NotNull(message = "{chat.startDate.invalid}")
  @Schema(required = true, example = "2019-10-23")
  @JsonProperty("startDate")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @DateTimeFormat(pattern = "HH:mm")
  @NotNull(message = "{chat.startTime.invalid}")
  @Schema(required = true, example = "12:05")
  @JsonProperty("startTime")
  private LocalTime startTime;

  @NotNull(message = "{chat.duration.notNull}")
  @Min(value = CHAT_MIN_DURATION, message = "{chat.duration.invalid}")
  @Max(value = CHAT_MAX_DURATION, message = "{chat.duration.invalid}")
  @Schema(required = true, example = "120")
  @JsonProperty("duration")
  private int duration;

  @NotNull(message = "{chat.repetitive.notNull}")
  @Schema(required = true, example = "true")
  @JsonProperty("repetitive")
  private boolean repetitive;

  @Schema(required = true, example = "5")
  @Min(value = 0, message = "{chat.agencyId.invalid}")
  @JsonProperty("agencyId")
  private Long agencyId;

  @Schema(required = true, example = "5")
  @Length(max = 300, message = "{chat.hintMessage.invalid}")
  @JsonProperty("hintMessage")
  private String hintMessage;

  @Override
  public String toString() {
    return "ChatDTO [topic="
        + topic
        + ", agencyId="
        + agencyId
        + ", startDate="
        + startDate
        + ", startTime="
        + startTime
        + ", duration="
        + duration
        + ", repetitive="
        + repetitive
        + ", agencyId="
        + agencyId
        + ", hintMessage="
        + hintMessage
        + "]";
  }
}
