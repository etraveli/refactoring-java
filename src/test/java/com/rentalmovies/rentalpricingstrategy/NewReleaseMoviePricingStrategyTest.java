package com.rentalmovies.rentalpricingstrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NewReleaseMoviePricingStrategyTest
{
    private RentalPricingStrategy newReleaseMovie;

    @BeforeEach
    void init()
    {
        newReleaseMovie = new NewReleaseMoviePricingStrategy();
    }

    @Test
    public void testCalculateRentalAmount()
    {
        int daysRented = 5;
        double expected = 15.0;
        double actual = newReleaseMovie.calculateRentalAmount(daysRented);
        assertEquals(expected, actual, "The rental amount should be calculated as daysRented * 3");
    }

    @Test
    public void testGetFrequentRenterPointsMoreThan2Days()
    {
        int daysRented = 3;
        int expected = 2;
        int actual = newReleaseMovie.getFrequentRenterPoints(daysRented);
        assertEquals(expected, actual, "Should return 2 frequent renter points for rentals of more than 2 days");
    }

    @Test
    public void testGetFrequentRenterPointsLessThanOrEqualTo2Days()
    {
        int daysRented = 2;
        int expected = 1;
        int actual = newReleaseMovie.getFrequentRenterPoints(daysRented);
        assertEquals(expected, actual, "Should return 1 frequent renter point for rentals of 2 days or less");
    }

    @Test
    public void testCalculateRentalAmountForZeroDays()
    {
        int daysRented = 0;
        double expected = 0;
        double actual = newReleaseMovie.calculateRentalAmount(daysRented);
        assertEquals(expected, actual, "The rental amount should be 0 for 0 days rented");
    }
}
