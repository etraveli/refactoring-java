package com.etraveliGroup.exception;

public class InvalidCustomerException extends RuntimeException {

    public InvalidCustomerException() {}

    public InvalidCustomerException(String msg)
    {
        super(msg);
    }
}
