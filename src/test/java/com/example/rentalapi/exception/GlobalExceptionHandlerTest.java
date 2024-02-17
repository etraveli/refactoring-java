package com.example.rentalapi.exception;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.rentalapi.constants.RentalConstants;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_INVALID_CUSTOMER_EXCEPTION)
    public void handleInvalidCustomerException_ShouldReturnBadRequest() {
        InvalidCustomerException exception = new InvalidCustomerException("Invalid customer");
        ResponseEntity<String> response = globalExceptionHandler.handleInvalidCustomerException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Invalid customer", response.getBody());
    }

    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_INVALID_MOVIE_EXCEPTION)
    public void handleInvalidMovieException_ShouldReturnBadRequest() {
        InvalidMovieException exception = new InvalidMovieException("Invalid movie");
        ResponseEntity<String> response = globalExceptionHandler.handleInvalidMovieException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Invalid movie", response.getBody());
    }

    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_UNKNOWN_EXCEPTION)
    void handleUnknownException_ShouldReturnInternalServerError() {
        Exception exception = new RuntimeException("Unknown error");
        ResponseEntity<String> response = globalExceptionHandler.handleUnknownException(exception);
        assertEquals("An unexpected error occurred: Unknown error", response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    
    @Test
    @DisplayName(RentalConstants.TEST_CASE_DISPLAYNAME_INVALID_MOVIE_CODE_EXCEPTION)
    void handleInvalidMovieCodeException_ShouldReturnBadRequest() {
        InvalidMovieCodeException exception = new InvalidMovieCodeException("Invalid movie code");
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleInvalidMovieCodeException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().contains(RentalConstants.ERROR_TEXT));
        assertTrue(responseEntity.getBody().contains(exception.getMessage()));
    }
}
