package de.caritas.cob.userservice.api.exception.rocketchat;

import java.io.Serial;

public class RocketChatLeaveFromGroupException extends Exception {

  @Serial private static final long serialVersionUID = 2106829666296656057L;

  /**
   * Exception, when a Rocket.Chat API call to leave a group fails
   *
   * @param ex
   */
  public RocketChatLeaveFromGroupException(String message) {
    super(message);
  }
}
