package de.caritas.cob.userservice.api.exception;

import java.io.Serial;

public class NewMessageNotificationException extends RuntimeException {

  @Serial private static final long serialVersionUID = 5573541126570935402L;

  /**
   * New message notification exception
   *
   * @param message
   */
  public NewMessageNotificationException(String message) {
    super(message);
  }

  /**
   * New message notification exception
   *
   * @param message
   * @param ex
   */
  public NewMessageNotificationException(String message, Exception ex) {
    super(message, ex);
  }
}
