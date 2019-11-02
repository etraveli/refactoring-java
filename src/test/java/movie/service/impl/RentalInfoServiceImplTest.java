package movie.service.impl;

import movie.CustomerCreator;
import movie.dto.Customer;
import movie.dto.MovieRental;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RentalInfoServiceImplTest {

    private RentalInfoServiceImpl rentalInfoService = new RentalInfoServiceImpl();

    @Test
    public void fewMoviesShouldHaveMultipleRecords(){
        String expected = "Rental Record for C. U. Stomer\n\t" +
                "You've Got Mail\t" +
                "3.5\n\t" +
                "Matrix\t" +
                "2.0\n" +
                "Amount owed is 5.5\n" +
                "You earned 2 frequent points\n";

        String result = rentalInfoService.getStatementForCustomer(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        assertThat(result, is(expected));
    }

    @Test
    public void oneMovieRentalShouldHaveOneRecord(){
        String expected = "Rental Record for Iurii\n\t" +
                "You've Got Mail\t" +
                "3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent points\n";

        String result = rentalInfoService.getStatementForCustomer(
                CustomerCreator.createCustomer("Iurii", Collections.singletonList(new MovieRental("F001", 3))));

        assertThat(result, is(expected));
    }

    @Test
    public void fewMoviesWithFrequentPointsForNew(){
        String expected = "Rental Record for Iurii\n\t" +
                "You've Got Mail\t" +
                "3.5\n\t" +
                "Cars\t" +
                "3.0\n\t" +
                "Fast & Furious X\t" +
                "15.0\n\t" +
                "Matrix\t" +
                "8.0\n" +
                "Amount owed is 29.5\n" +
                "You earned 5 frequent points\n";

        String result = rentalInfoService.getStatementForCustomer(
                CustomerCreator.createCustomer("Iurii", Arrays.asList(
                        new MovieRental("F001", 3),
                        new MovieRental("F003", 4),
                        new MovieRental("F004", 5),
                        new MovieRental("F002", 6))));

        assertThat(result, is(expected));
    }

    @Test
    public void fewMoviesWithoutFrequentPointsForNew(){
        String expected = "Rental Record for Iurii\n\t" +
                "You've Got Mail\t" +
                "3.5\n\t" +
                "Cars\t" +
                "3.0\n\t" +
                "Fast & Furious X\t" +
                "6.0\n\t" +
                "Matrix\t" +
                "8.0\n" +
                "Amount owed is 20.5\n" +
                "You earned 4 frequent points\n";

        String result = rentalInfoService.getStatementForCustomer(
                CustomerCreator.createCustomer("Iurii", Arrays.asList(
                        new MovieRental("F001", 3),
                        new MovieRental("F003", 4),
                        new MovieRental("F004", 2),
                        new MovieRental("F002", 6))));

        assertThat(result, is(expected));
    }
}