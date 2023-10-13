package com.rentalmovies.statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.rentalmovies.models.Customer;
import com.rentalmovies.models.Movie;
import com.rentalmovies.models.MovieRental;
import com.rentalmovies.moviestore.MovieStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TextStatementGeneratorTest
{
    private TextStatementGenerator generator;
    private Customer customer;
    private MovieStore movieStore;

    @BeforeEach
    void init()
    {
        generator = new TextStatementGenerator();
        customer = mock(Customer.class);
        movieStore = mock(MovieStore.class);
        MovieRental rental1 = createMockRental("1", 2, "Movie 1", 3.0, 1);
        MovieRental rental2 = createMockRental("2", 3, "Movie 2", 4.5, 2);

        when(customer.getCustomerName()).thenReturn("John Doe");
        when(customer.getMovieRentals()).thenReturn(Arrays.asList(rental1, rental2));

    }

    private MovieRental createMockRental(String movieId, int rentalDays, String title, double rentalAmount, int renterPoints)
    {
        MovieRental rental = mock(MovieRental.class);
        Movie movie = mock(Movie.class);

        when(rental.getMovieId()).thenReturn(movieId);
        when(rental.getRentalDays()).thenReturn(rentalDays);
        when(movieStore.getMovie(movieId)).thenReturn(movie);
        when(movie.getTitle()).thenReturn(title);
        when(movie.calculateRentalAmount(rentalDays)).thenReturn(rentalAmount);
        when(movie.getFrequentRenterPoints(rentalDays)).thenReturn(renterPoints);

        return rental;
    }

    @Test
    void testGenerate()
    {
        String expected = new StringBuilder()
                .append("Rental Record for John Doe\n")
                .append("\tMovie 1\t3.0\n")
                .append("\tMovie 2\t4.5\n")
                .append("Amount owed is 7.5\n")
                .append("You earned 3 frequent points\n")
                .toString();

        assertEquals(expected, generator.generateStatement(customer, movieStore));
    }
}
