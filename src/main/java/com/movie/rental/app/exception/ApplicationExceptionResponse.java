package com.movie.rental.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * The ApplicationExceptionResponse class is used as base response class for exceptions
 *
 * @author pabasara8857@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
public class ApplicationExceptionResponse implements Serializable {
  /** error message */
  private Object errorMessage;

  /** error return code */
  private int returnCode;

  /** error occurred at */
  private ZonedDateTime timestamp;
}
