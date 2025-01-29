package de.caritas.cob.userservice.api.exception.rocketchat;

import java.io.Serial;

public class RocketChatGetGroupMembersException extends Exception {

  @Serial private static final long serialVersionUID = -6467348860210122736L;

  /**
   * Exception, when a Rocket.Chat API call to get group m embers fails.
   *
   * @param ex the caused exception
   */
  public RocketChatGetGroupMembersException(Exception ex) {
    super(ex);
  }

  /**
   * Exception, when a Rocket.Chat API call to get group m embers fails.
   *
   * @param message an additional message
   */
  public RocketChatGetGroupMembersException(String message) {
    super(message);
  }

  /**
   * Exception, when a Rocket.Chat API call to get group m embers fails.
   *
   * @param message an additional message
   * @param ex the caused exception
   */
  public RocketChatGetGroupMembersException(String message, Exception ex) {
    super(message, ex);
  }
}
