package de.caritas.cob.userservice.api.exception;

import java.io.Serial;

public class MissingConsultingTypeException extends RuntimeException {

  @Serial private static final long serialVersionUID = -6127271234647444277L;

  /**
   * Exception, when settings for an requested consulting type are missing
   *
   * @param message
   */
  public MissingConsultingTypeException(String message) {
    super(message);
  }
}
