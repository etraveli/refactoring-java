package com.rentalmovies.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRentalTest
{
    @Test
    @DisplayName ("Should create MovieRental with valid movieId and rentalDays")
    void shouldCreateMovieRentalWithValidInputs()
    {
        MovieRental rental = new MovieRental("Movie1", 5);
        assertAll(
                () -> assertEquals("Movie1", rental.getMovieId()),
                () -> assertEquals(5, rental.getRentalDays()));
    }

    @Test
    @DisplayName ("Should throw IllegalArgumentException for invalid movieId")
    void shouldThrowExceptionForInvalidMovieId()
    {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new MovieRental("", 5), "String cannot be null or blank"),
                () -> assertThrows(IllegalArgumentException.class, () -> new MovieRental("   ", 5), "String cannot be null or blank"),
                () -> assertThrows(IllegalArgumentException.class, () -> new MovieRental(null, 5), "String cannot be null or blank"));
    }

    @Test
    @DisplayName ("Should throw IllegalArgumentException for invalid rentalDays")
    void shouldThrowExceptionForInvalidRentalDays()
    {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new MovieRental("Movie1", 0), "Rental days should be positive"),
                () -> assertThrows(IllegalArgumentException.class, () -> new MovieRental("Movie1", -1), "Rental days should be positive"));
    }
}
