package com.movie.rental.customexception;

@SuppressWarnings("serial")
public class InvalidRentDaysException extends RuntimeException{

    public InvalidRentDaysException() {}

    public InvalidRentDaysException(String msg)
    {
        super(msg);
    }
}