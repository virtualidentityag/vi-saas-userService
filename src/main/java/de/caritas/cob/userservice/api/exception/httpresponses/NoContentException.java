package de.caritas.cob.userservice.api.exception.httpresponses;

import de.caritas.cob.userservice.api.service.LogService;
import java.io.Serial;

public class NoContentException extends CustomHttpStatusException {

  @Serial private static final long serialVersionUID = -4160810917274267137L;

  /**
   * No content exception.
   *
   * @param message the message
   */
  public NoContentException(String message) {
    super(message, LogService::logWarn);
  }
}
