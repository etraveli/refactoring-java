package com.rentalmovies.rentalstatementservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.rentalmovies.models.Customer;
import com.rentalmovies.models.MovieRental;
import com.rentalmovies.moviestore.MovieStore;
import com.rentalmovies.statement.AbstractStatementGenerator;
import com.rentalmovies.statementservice.RentalStatementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

public class RentalStatementServiceTest
{
    private AbstractStatementGenerator statementGenerator;
    private MovieStore movieStore;
    private RentalStatementService rentalStatementService;

    @BeforeEach
    void init()
    {
        statementGenerator = mock(AbstractStatementGenerator.class);
        movieStore = mock(MovieStore.class);
        rentalStatementService = new RentalStatementService(statementGenerator, movieStore);
    }

    @Test
    public void testStatement()
    {
        List<MovieRental> rentals = Arrays.asList(
                new MovieRental("Movie1", 2),
                new MovieRental("Movie2", 3));

        Customer customer = new Customer("John Doe", rentals);
        String expectedStatement = buildExpectedResult();

        when(statementGenerator.generateStatement(any(Customer.class), eq(movieStore))).thenReturn(expectedStatement);

        String actualStatement = rentalStatementService.getStatement(customer);

        assertEquals(expectedStatement, actualStatement);
        verify(statementGenerator).generateStatement(customer, movieStore);
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(statementGenerator).generateStatement(captor.capture(), eq(movieStore));
        assertEquals(customer, captor.getValue());
    }

    private String buildExpectedResult()
    {
        return new StringBuilder()
                .append("Rental Record for John Doe\n")
                .append("\tMovie1\t2.0\n")
                .append("\tMovie2\t3.0\n")
                .append("Amount owed is 5.0\n")
                .append("You earned 2 frequent renter points")
                .toString();
    }
}
