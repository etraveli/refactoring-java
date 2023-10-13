package com.rentalmovies.rentalpricingstrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegularMovieTest
{
    private RentalPricingStrategy regularMovie;

    @BeforeEach
    void init()
    {
        regularMovie = new RegularMoviePricingStrategy();
    }

    @Test
    public void whenDaysRentedIsLessThanEqualToTwo_thenCorrectRentalAmount()
    {
        int daysRented = 2;
        double expected = 2.0;
        double actual = regularMovie.calculateRentalAmount(daysRented);
        assertEquals(expected, actual, 0.01, "Rental amount should be 2 for 2 days or less");
    }

    @Test
    public void whenDaysRentedIsMoreThanTwo_thenCorrectRentalAmount()
    {
        int daysRented = 4;
        double expected = 5.0;
        double actual = regularMovie.calculateRentalAmount(daysRented);
        assertEquals(expected, actual, 0.01, "Rental amount should consider extra days after first 2 days");
    }

    @Test
    public void whenDaysRentedIsProvided_thenAlwaysReturnOneFrequentRenterPoint()
    {
        int daysRented = 5; // Value does not matter as the outcome is always 1
        int expected = 1;
        int actual = regularMovie.getFrequentRenterPoints(daysRented);
        assertEquals(expected, actual, "Frequent renter points should always be 1 for regular movies");
    }
}
