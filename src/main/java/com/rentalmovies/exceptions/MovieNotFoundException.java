package com.rentalmovies.exceptions;

public class MovieNotFoundException extends RuntimeException
{
    public MovieNotFoundException(String movieId)
    {
        super("Movie with ID: " + movieId + " not found.");
    }

    public MovieNotFoundException(String movieId, Throwable throwable)
    {
        super("Movie with ID: " + movieId + " not found.", throwable);
    }
}
