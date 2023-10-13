package com.rentalmovies.statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.rentalmovies.models.Customer;
import com.rentalmovies.models.MovieRental;
import com.rentalmovies.moviestore.MovieStore;
import com.rentalmovies.rentalservice.RentCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TextStatementGeneratorTest
{
    private TextStatementGenerator generator;

    @BeforeEach
    void setUp()
    {
        generator = new TextStatementGenerator(new RentCalculationService());

    }

    @Test
    void testHeader()
    {
        assertEquals("Rental Record for Bob\n", generator.getHeader("Bob"));
    }

    @Test
    void testDetail()
    {
        assertEquals("\tHello brother\t3.0\n", generator.getDetail("Hello brother", 3.0));
    }

    @Test
    void testFooter()
    {
        assertEquals("Amount owed is 10.0\nYou earned 2 frequent points\n", generator.getFooter(10.0, 2));
    }

    @Test
    void testGenerateStatement()
    {
        List<MovieRental> rentals = Arrays.asList(
                new MovieRental("F001", 3),
                new MovieRental("F002", 1));
        Customer customer = new Customer("C. U. Stomer", rentals);
        assertEquals(buildExpectedResult(), generator.generateStatement(customer, new MovieStore()));
    }

    private static String buildExpectedResult()
    {
        return new StringBuilder()
                .append("Rental Record for C. U. Stomer\n")
                .append("\tYou've Got Mail\t3.5\n")
                .append("\tMatrix\t2.0\n")
                .append("Amount owed is 5.5\n")
                .append("You earned 2 frequent points\n")
                .toString();
    }
}
