package com.example.rentalapi.exception;
@SuppressWarnings("serial")
public class InvalidRentalDaysException extends RuntimeException {
    public InvalidRentalDaysException(String message) {
        super(message);
    }
}