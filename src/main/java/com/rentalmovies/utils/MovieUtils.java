package com.rentalmovies.utils;

public final class MovieUtils
{
    private MovieUtils()
    {
        // Private constructor to prevent instantiation
    }

    public static String validateString(String text)
    {
        if (text == null || text.trim().isEmpty())
        {
            throw new IllegalArgumentException("String cannot be null or blank");
        }
        return text.trim();
    }

    public static int validateRentalDays(int rentalDays)
    {
        if (rentalDays <= 0)
        {
            throw new IllegalArgumentException("Rental days should be positive");
        }
        return rentalDays;
    }
}
