package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieRentalTest {
    @Test
    public void givenValidParameters_whenCreatingRental_thenDoNotThrow() {
        assertDoesNotThrow(() -> new MovieRental(Movie.F002, 2));
    }
    @Test
    public void givenPassingMovieAsNull_whenCreatingRental_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,() -> {
            new MovieRental(null, 2);
        });
    }
}