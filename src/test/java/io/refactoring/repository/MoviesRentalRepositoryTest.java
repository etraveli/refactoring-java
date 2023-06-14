package io.refactoring.repository;

import io.refactoring.repository.impl.MoviesRentalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoviesRentalRepositoryTest {

    private MoviesRentalRepository tested = new MoviesRentalRepository("src/test/resources/movies.txt");

    @Test
    public void testValidMovieId(){
        Assertions.assertEquals(tested.movie("F001").title(), "You've Got Mail");
    }

    @Test
    public void testInvalidMovieId(){
        Assertions.assertThrows(RuntimeException.class,
                () -> tested.movie("F0010"));
    }

    @Test
    public void testInvalidFilePath(){
        tested = new MoviesRentalRepository("src/resources/movies.txt");
        Assertions.assertThrows(RuntimeException.class,
                () -> tested.movie("F0010"));
    }

}
