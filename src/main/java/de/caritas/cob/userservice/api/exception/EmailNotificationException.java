package de.caritas.cob.userservice.api.exception;

import java.io.Serial;

public class EmailNotificationException extends RuntimeException {

  @Serial private static final long serialVersionUID = -2260264511268139498L;

  /**
   * E-mail notification exception
   *
   * @param message
   */
  public EmailNotificationException(String message) {
    super(message);
  }

  /**
   * E-mail notification exception
   *
   * @param message
   * @param ex
   */
  public EmailNotificationException(String message, Exception ex) {
    super(message, ex);
  }

  /**
   * E-mail notification exception
   *
   * @param ex
   */
  public EmailNotificationException(Exception ex) {
    super(ex);
  }
}
