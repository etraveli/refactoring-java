package io.refactoring.service;

import io.refactoring.model.Customer;
import io.refactoring.model.MovieRental;
import io.refactoring.repository.impl.MoviesRentalRepository;
import io.refactoring.service.impl.RentalCalculator;
import io.refactoring.service.impl.RentalInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

public class RentalInfoTest {

    private MoviesRentalRepository moviesRentalRepository = new MoviesRentalRepository("src/test/resources/movies.txt");
    private RentalCalculator rentalCalculator = new RentalCalculator(moviesRentalRepository);
    RentalInfo rentalInfo = new RentalInfo(rentalCalculator, moviesRentalRepository);
    @Test
    public void testValid() throws IOException {
        String expected = "Rental Record for R. A. Mathews\n\tCars\t1.5\n\tFast & Furious X\t3.0\nAmount owed is 4.5\nYou earned 2 frequent points\n";
        String actual = rentalInfo.statement(
                new Customer(
                        "R. A. Mathews",
                        Arrays.asList(
                                new MovieRental("F003", 3),
                                new MovieRental("F004", 1)
                        )
                )
        );
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testInValid() throws IOException {
        String expected = "Rental Record for R. A. Mathews\n\tCars\t1.5\n\tFast & Furious X\t3.0\nAmount owed is 4.5\nYou earned 2 frequent points\n";
        String actual = rentalInfo.statement(
                new Customer(
                        "R. A. Mathews",
                        Arrays.asList(
                                new MovieRental("F003", 4),
                                new MovieRental("F004", 11)
                        )
                )
        );
        Assertions.assertNotEquals(actual, expected);
    }

}
