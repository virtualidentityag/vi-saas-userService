package de.caritas.cob.userservice.api.exception.httpresponses;

import java.io.Serial;

public class RocketChatUnauthorizedException extends RuntimeException {

  @Serial private static final long serialVersionUID = -3553609955386498237L;

  public RocketChatUnauthorizedException(final String userId, final Throwable exception) {
    super(
        "Could not get Rocket.Chat subscriptions for user ID "
            + userId
            + ": Token is not active (401 Unauthorized)",
        exception);
  }
}
