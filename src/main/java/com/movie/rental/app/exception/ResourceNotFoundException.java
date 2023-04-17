package com.movie.rental.app.exception;

/**
 * The ResourceNotFoundException class is used to throw exception when the requested resource is not
 * found
 *
 * @author pabasara8857@gmail.com
 */
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
