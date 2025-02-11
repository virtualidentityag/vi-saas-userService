package de.caritas.cob.userservice.api.adapters.web.controller;

import de.caritas.cob.userservice.api.service.statistics.SessionStatisticsService;
import de.caritas.cob.userservice.api.statistics.model.SessionStatisticsResultDTO;
import de.caritas.cob.userservice.generated.api.statistics.controller.UserstatisticsApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/** Controller to handle all statistics requests. */
@RestController
@Tag(name = "user-statistics-controller")
@RequiredArgsConstructor
public class UserStatisticsController implements UserstatisticsApi {

  private final @NonNull SessionStatisticsService sessionStatisticsService;

  /**
   * Retrieve a session via session id or Rocket.Chat group id.
   *
   * @param sessionId The id of the session.
   * @param rcGroupId The rc group id of the session. if the session id is also passed, the query is
   *     done via it.
   * @return a {@link SessionStatisticsResultDTO} instance
   */
  @Override
  public ResponseEntity<SessionStatisticsResultDTO> getSession(Long sessionId, String rcGroupId) {
    return new ResponseEntity<>(
        sessionStatisticsService.retrieveSession(sessionId, rcGroupId), HttpStatus.OK);
  }
}
