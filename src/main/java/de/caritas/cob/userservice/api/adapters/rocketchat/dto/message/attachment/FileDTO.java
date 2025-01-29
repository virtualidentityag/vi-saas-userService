package de.caritas.cob.userservice.api.adapters.rocketchat.dto.message.attachment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Rocket.Chat file model (sub of MessagesDTO.lastMessage) */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

  @Schema(required = true, example = "filename.png")
  private String name;

  @Schema(
      required = true,
      example = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
  @JsonProperty("type")
  private String type;
}
