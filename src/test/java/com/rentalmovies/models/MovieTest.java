package com.rentalmovies.models;

import com.rentalmovies.rentalpricingstrategy.RentalPricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovieTest
{
    private Movie movie;
    private RentalPricingStrategy pricingStrategy;

    private static final String TITLE = "MovieTitle";

    @BeforeEach
    void init()
    {
        pricingStrategy = Mockito.mock(RentalPricingStrategy.class);
        Mockito.when(pricingStrategy.calculateRentalAmount(Mockito.anyInt())).thenReturn(10.0);
        Mockito.when(pricingStrategy.getFrequentRenterPoints(Mockito.anyInt())).thenReturn(2);

        movie = new Movie(TITLE, pricingStrategy);
    }

    @Test
    void testMovieCreation()
    {
        assertEquals(TITLE, movie.getTitle());
    }

    @Test
    void testInvalidTitle()
    {
        assertThrows(IllegalArgumentException.class, () -> new Movie("", pricingStrategy));
    }

    @Test
    void testNullPricingStrategy()
    {
        assertThrows(NullPointerException.class, () -> new Movie(TITLE, null));
    }

    @Test
    void testCalculateRentalAmount()
    {
        double rentalAmount = movie.calculateRentalAmount(5);
        assertEquals(10.0, rentalAmount);
        Mockito.verify(pricingStrategy).calculateRentalAmount(5);
    }

    @Test
    void testGetFrequentRenterPoints()
    {
        int points = movie.getFrequentRenterPoints(5);
        assertEquals(2, points);
        Mockito.verify(pricingStrategy).getFrequentRenterPoints(5);
    }
}
