package com.etraveli.movie.service.exception;

import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementEx(NoSuchElementException ex) {

        log.error(NoSuchElementException.class.getName() + ": " + ex.getMessage());
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errorMessage(ex.getMessage())
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgEx(IllegalArgumentException ex) {

        log.error(IllegalArgumentException.class.getName() + ": " + ex.getMessage());
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errorMessage(ex.getMessage())
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .build()
                , HttpStatus.BAD_REQUEST);
    }
}
