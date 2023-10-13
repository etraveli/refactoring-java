package com.rentalmovies.exceptions;

public class CustomerNotFoundException extends RuntimeException
{
    public CustomerNotFoundException(String customerId)
    {
        super("Movie with ID: " + customerId + " not found.");
    }

    public CustomerNotFoundException(String customerId, Throwable throwable)
    {
        super("Movie with ID: " + customerId + " not found.", throwable);
    }
}
