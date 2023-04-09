package com.etraveliGroup.exception;

public class InvalidDaysException extends RuntimeException{

    public InvalidDaysException() {}

    public InvalidDaysException(String msg)
    {
        super(msg);
    }
}
