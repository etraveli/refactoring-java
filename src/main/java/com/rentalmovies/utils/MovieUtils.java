package com.rentalmovies.utils;

public final class MovieUtils
{
    private MovieUtils()
    {
        // Private constructor to prevent instantiation
    }

    /**
     * Validates a given string, ensuring it is neither null nor empty. 
     * It trims the string before performing the check. 
     *
     * @param text The string to be validated.
     * @return The validated and trimmed string.
     * @throws IllegalArgumentException If the provided string is null or empty.
     */
    public static String validateString(String text)
    {
        if (text == null || text.trim().isEmpty())
        {
            throw new IllegalArgumentException("String cannot be null or blank");
        }
        return text.trim();
    }

    /**
     * Validates the provided number of rental days, ensuring it is a positive number.
     *
     * @param rentalDays The number of rental days to validate.
     * @return The validated number of rental days.
     * @throws IllegalArgumentException If the provided number of rental days is not positive.
     */
    public static int validateRentalDays(int rentalDays)
    {
        if (rentalDays <= 0)
        {
            throw new IllegalArgumentException("Rental days should be positive");
        }
        return rentalDays;
    }
}
