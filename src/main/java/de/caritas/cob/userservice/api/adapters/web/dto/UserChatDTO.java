package de.caritas.cob.userservice.api.adapters.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Represents the chat for the user
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "UserChat")
public class UserChatDTO {

  @Schema(example = "153918")
  private Long id;

  @Schema(example = "Drugs")
  private String topic;

  @Schema(required = true, example = "2019-10-23")
  private LocalDate startDate;

  @Schema(required = true, example = "12:05")
  private LocalTime startTime;

  @Schema(required = true, example = "120")
  private int duration;

  @Schema(required = true, example = "true")
  private boolean repetitive;

  @Schema(required = true, example = "false")
  private boolean active;

  @Schema(required = true, example = "0")
  private Integer consultingType;

  @Schema(example = "Thanks for the answer")
  private String lastMessage;

  @Schema(example = "1539184948")
  private Long messageDate;

  @Schema(example = "false")
  private boolean messagesRead;

  @Schema(example = "xGklslk2JJKK")
  private String groupId;

  @Schema private SessionAttachmentDTO attachment;

  @Schema(example = "false")
  private boolean subscribed;

  @Schema(example = "ajsasdkjsdfkj3, 23njds9f8jhi")
  private String[] moderators;

  @JsonIgnore private LocalDateTime startDateWithTime;

  @Schema private LastMessageDTO e2eLastMessage;

  @Schema private String createdAt;

  @Schema private List<AgencyDTO> assignedAgencies;

  @Schema private String hintMessage;
}
