package io.refactoring.service;

import io.refactoring.model.MovieRental;
import io.refactoring.repository.impl.MoviesRentalRepository;
import io.refactoring.service.impl.RentalCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RentalCalculatorTest {

    private MoviesRentalRepository moviesRentalRepository = new MoviesRentalRepository("src/test/resources/movies.txt");
    private RentalCalculator tested = new RentalCalculator(moviesRentalRepository);

    @Test
    public void testValid(){
        MovieRental movieRental1 = new MovieRental("F004", 4);
        MovieRental movieRental2 = new MovieRental("F002", 3);
        MovieRental movieRental3 = new MovieRental("F003", 3);
        Assertions.assertEquals(tested.rent(movieRental1), 12);
        Assertions.assertEquals(tested.rent(movieRental2), 3.5);
        Assertions.assertEquals(tested.rent(movieRental3), 1.5);
    }

    @Test
    public void testInValid(){
        MovieRental movieRental1 = new MovieRental("F004", 4);
        MovieRental movieRental2 = new MovieRental("F002", 3);
        MovieRental movieRental3 = new MovieRental("F003", 3);
        Assertions.assertNotEquals(tested.rent(movieRental1), 10);
        Assertions.assertNotEquals(tested.rent(movieRental2), 12);
        Assertions.assertNotEquals(tested.rent(movieRental3), 4.5);
    }

}
