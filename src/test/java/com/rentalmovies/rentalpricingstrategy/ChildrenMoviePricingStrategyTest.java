package com.rentalmovies.rentalpricingstrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChildrenMoviePricingStrategyTest
{
    private RentalPricingStrategy childrenMovie;

    @BeforeEach
    void init()
    {
        childrenMovie = new ChildrenMoviePricingStrategy();
    }

    @Test
    public void testCalculateRentalAmountForLessThan3Days()
    {
        double expected = 1.5;
        double actual = childrenMovie.calculateRentalAmount(2);
        assertEquals(expected, actual, "The rental amount for less than 3 days should be 1.5");
    }

    @Test
    public void testCalculateRentalAmountForMoreThan3Days()
    {
        int daysRented = 5;
        double expected = 4.5;
        double actual = childrenMovie.calculateRentalAmount(daysRented);
        assertEquals(expected, actual, "The rental amount for more than 3 days should be calculated correctly");
    }

    @Test
    public void testGetFrequentRenterPoints()
    {
        int expected = 1;
        int actual = childrenMovie.getFrequentRenterPoints(5);
        assertEquals(expected, actual, "The frequent renter points should always be 1 for children's movies");
    }
}
