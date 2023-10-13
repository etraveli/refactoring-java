package com.rentalmovies.models;

import static com.rentalmovies.utils.MovieUtils.validateString;
import static com.rentalmovies.utils.MovieUtils.validateRentalDays;

/**
 * Making a class immutable should be a priority, especially in environments
 * where concurrency, security, and data consistency are paramount.
 */
public final class MovieRental
{
    private final String movieId;
    private final int rentalDays;

    public MovieRental(String movieIdentifier, int days)
    {
        movieId = validateString(movieIdentifier);
        rentalDays = validateRentalDays(days);
    }

    public String getMovieId()
    {
        return movieId;
    }

    public int getRentalDays()
    {
        return rentalDays;
    }

    @Override
    public String toString()
    {
        return String.format("MovieRental{movieId='%s', days=%d}", movieId, rentalDays);
    }
}
