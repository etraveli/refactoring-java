package refactoring.java.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieRentalTest {

    @Test
    void getMovieId() {
        MovieRental movieRental = new MovieRental("F001", -1);
        assertEquals("F001", movieRental.getMovieId());
    }

    @Test
    void getDays() {
        MovieRental movieRental = new MovieRental(null, 3);
        assertEquals(3, movieRental.getDays());
    }
}