package de.caritas.cob.userservice.api.exception;

import java.io.Serial;

public class MessageHasAlreadyBeenSavedException extends Exception {

  @Serial private static final long serialVersionUID = 3067609195162892096L;

  /**
   * Enquiry message conflict exception.
   *
   * @param message the exception message
   */
  public MessageHasAlreadyBeenSavedException(String message) {
    super(message);
  }

  /** Enquiry message conflict exception. */
  public MessageHasAlreadyBeenSavedException() {
    super();
  }
}
