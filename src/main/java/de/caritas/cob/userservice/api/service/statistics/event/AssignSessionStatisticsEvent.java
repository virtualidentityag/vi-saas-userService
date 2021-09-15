package de.caritas.cob.userservice.api.service.statistics.event;

import de.caritas.cob.userservice.api.helper.JSONHelper;
import de.caritas.cob.userservice.api.service.LogService;
import de.caritas.cob.userservice.offsetdatetime.CustomOffsetDateTime;
import de.caritas.cob.userservice.statisticsservice.generated.web.model.AssignSessionStatisticsEventMessage;
import de.caritas.cob.userservice.statisticsservice.generated.web.model.EventType;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignSessionStatisticsEvent implements StatisticsEvent {

  private static final EventType EVENT_TYPE = EventType.ASSIGN_SESSION;

  private @NonNull String consultantId;
  private @NonNull Long sessionId;

  /** {@inheritDoc} */
  public EventType getEventType() {
    return EVENT_TYPE;
  }

  /** {@inheritDoc} */
  @Override
  public Optional<String> getPayload() {
    return JSONHelper.serializeWithOffsetDateTimeAsString(
        createAssignSessionStatisticsEventMessage(), LogService::logStatisticsEventError);
  }

  private AssignSessionStatisticsEventMessage createAssignSessionStatisticsEventMessage() {
    return new AssignSessionStatisticsEventMessage()
        .eventType(EVENT_TYPE)
        .consultantId(this.consultantId)
        .sessionId(this.sessionId)
        .timestamp(CustomOffsetDateTime.nowInUtc());
  }
}
