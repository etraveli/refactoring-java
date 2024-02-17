package com.example.rentalapi.exception;
@SuppressWarnings("serial")
public class InvalidMovieCodeException extends RuntimeException {
    public InvalidMovieCodeException(String message) {
        super(message);
    }
}