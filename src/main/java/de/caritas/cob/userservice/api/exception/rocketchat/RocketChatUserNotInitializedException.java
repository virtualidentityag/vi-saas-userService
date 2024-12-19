package de.caritas.cob.userservice.api.exception.rocketchat;

import java.io.Serial;

public class RocketChatUserNotInitializedException extends Exception {

  @Serial private static final long serialVersionUID = -6444815503348502528L;

  public RocketChatUserNotInitializedException(String message) {
    super(message);
  }
}
