package de.caritas.cob.userservice.api.exception;

import java.io.Serial;

public class UpdateSessionException extends Exception {

  @Serial private static final long serialVersionUID = -3666710126372746391L;

  /**
   * Exception when update of session fails
   *
   * @param exception
   */
  public UpdateSessionException(Exception exception) {
    super(exception);
  }
}
