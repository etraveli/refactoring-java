package com.example.rentalapi.exception;
@SuppressWarnings("serial")
public class InvalidMovieException extends RuntimeException {
    public InvalidMovieException(String message) {
        super(message);
    }
    

}