package com.example.rentalapi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.rentalapi.constants.RentalConstants;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnknownException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RentalConstants.UNEXPECTED_EXCEPTION_MESSAGE + e.getMessage());
    }

	@ExceptionHandler(InvalidMovieCodeException.class)
    public ResponseEntity<String> handleInvalidMovieCodeException(InvalidMovieCodeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RentalConstants.ERROR_TEXT + e.getMessage());
    }

    @ExceptionHandler(InvalidCustomerException.class)
    public ResponseEntity<String> handleInvalidCustomerException(InvalidCustomerException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RentalConstants.ERROR_TEXT + e.getMessage());
    }
    
    @ExceptionHandler(InvalidMovieException.class)
    public ResponseEntity<String> handleInvalidMovieException(InvalidMovieException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RentalConstants.ERROR_TEXT + e.getMessage());
    } 
    
}
