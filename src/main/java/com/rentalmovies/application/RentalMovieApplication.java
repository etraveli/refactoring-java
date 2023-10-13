package com.rentalmovies.application;

import com.rentalmovies.models.Customer;
import com.rentalmovies.models.MovieRental;
import com.rentalmovies.moviestore.MovieStore;
import com.rentalmovies.statement.AbstractStatementGenerator;
import com.rentalmovies.statement.TextStatementGenerator;
import com.rentalmovies.statementservice.RentalStatementService;

import java.util.Arrays;
import java.util.List;

public class RentalMovieApplication
{
    public static void main(String[] args)
    {
        String expectedResult = buildExpectedResult();
        MovieStore movieStore = new MovieStore();
        AbstractStatementGenerator statementGenerator = new TextStatementGenerator();
        RentalStatementService rentalInfo = new RentalStatementService(statementGenerator, movieStore);

        List<MovieRental> rentals = Arrays.asList(
                new MovieRental("F001", 3),
                new MovieRental("F002", 1));

        Customer customer = new Customer("C. U. Stomer", rentals);
        String actualResult = rentalInfo.statement(customer);

        assertEqualResults(expectedResult.toString(), actualResult);
        System.out.println("Success");
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

    private static void assertEqualResults(String expected, String actual)
    {
        if (!actual.equals(expected))
        {
            String errorMessage = String.format("Expected:%n%s%n%nGot:%n%s", expected, actual);
            throw new AssertionError(errorMessage);
        }
    }
}
