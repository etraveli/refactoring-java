package com.example.rentalapi.exception;

@SuppressWarnings("serial")
public class InvalidCustomerException extends RuntimeException {

    public InvalidCustomerException(String message) {
        super(message);
    }


    
}