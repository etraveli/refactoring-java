package com.rentalmovies.models;

import com.rentalmovies.customer.model.Customer;
import com.rentalmovies.movierental.model.MovieRental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest
{
    private Customer customer;
    private MovieRental movieRental1;
    private MovieRental movieRental2;

    @BeforeEach
    void init()
    {
        movieRental1 = new MovieRental("1", 5);
        movieRental2 = new MovieRental("2", 3);
        customer = new Customer("Alice", "1", Arrays.asList(movieRental1, movieRental2));
    }

    @Test
    void testCustomerCreation()
    {
        assertEquals("Alice", customer.getCustomerName());
        assertEquals(2, customer.getMovieRentals().size());
        assertEquals(movieRental1, customer.getMovieRentals().get(0));
        assertEquals(movieRental2, customer.getMovieRentals().get(1));
    }

    @Test
    void testInvalidCustomerName()
    {
        assertThrows(IllegalArgumentException.class, () -> new Customer("","", Arrays.asList(movieRental1, movieRental2)));
    }

    @Test
    void testNullMovieRentals()
    {
        assertThrows(NullPointerException.class, () -> new Customer("Alice", "1", null));
    }

    @Test
    void testImmutableMovieRentals()
    {
        assertThrows(UnsupportedOperationException.class, () -> customer.getMovieRentals().add(new MovieRental("3", 7)));
    }

    @Test
    void testMovieRentalCreation()
    {
        assertEquals("1", movieRental1.getMovieId());
        assertEquals(5, movieRental1.getRentalDays());
    }

    @Test
    void testInvalidMovieId()
    {
        assertThrows(IllegalArgumentException.class, () -> new MovieRental("", 5));
    }

    @Test
    void testInvalidRentalDays()
    {
        assertThrows(IllegalArgumentException.class, () -> new MovieRental("1", 0));
    }
}
