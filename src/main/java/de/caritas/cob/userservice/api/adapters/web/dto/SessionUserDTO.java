package de.caritas.cob.userservice.api.adapters.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "SessionUser")
public class SessionUserDTO {

  @Schema(example = "id")
  private String id;

  @Schema(example = "Username")
  private String username;

  @Schema(example = "isDeleted")
  private boolean isDeleted;

  private Map<String, Object> sessionData;
}
