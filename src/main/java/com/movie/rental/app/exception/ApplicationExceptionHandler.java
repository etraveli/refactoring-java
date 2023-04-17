package com.movie.rental.app.exception;

import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The ApplicationExceptionHandler class is used to handle exceptions occurring in the application
 *
 * @author pabasara8857@gmail.com
 */
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handle resource not found exception
   *
   * @param ex ResourceNotFoundException
   * @param request WebRequest
   * @return ResponseEntity with exception message, error code and timestamp
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public final @ResponseBody ResponseEntity<ApplicationExceptionResponse>
      handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ApplicationExceptionResponse exceptionResponse =
        new ApplicationExceptionResponse(
            ex.getMessage(), HttpStatus.NOT_FOUND.value(), ZonedDateTime.now());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * Handle method argument not valid exception
   *
   * @param ex MethodArgumentNotValidException
   * @param headers HttpHeaders
   * @param status HttpStatusCode
   * @param request WebRequest
   * @return ResponseEntity with exception message, error code and timestamp
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    List<String> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());
    ApplicationExceptionResponse exceptionResponse =
        new ApplicationExceptionResponse(
            errors, HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle method argument type mismatch exception
   *
   * @param ex MethodArgumentTypeMismatchException
   * @param request WebRequest
   * @return ResponseEntity with exception message, error code and timestamp
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public final @ResponseBody ResponseEntity<ApplicationExceptionResponse>
      handleMethodArgumentTypeMismatchException(
          MethodArgumentTypeMismatchException ex, WebRequest request) {
    ApplicationExceptionResponse exceptionResponse =
        new ApplicationExceptionResponse(
            ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle http message not readable exception
   *
   * @param ex HttpMessageNotReadableException
   * @param headers HttpHeaders
   * @param status HttpStatusCode
   * @param request WebRequest
   * @return ResponseEntity with exception message, error code and timestamp
   */
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    ApplicationExceptionResponse exceptionResponse =
        new ApplicationExceptionResponse(
            ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
