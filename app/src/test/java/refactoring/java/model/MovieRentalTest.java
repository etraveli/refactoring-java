package refactoring.java.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRentalTest {

    @Test
    void getMovieId() {
        MovieRental movieRental = new MovieRental("F001", -1);
        assertEquals(movieRental.getMovieId(), "F001");
    }

    @Test
    void getDays() {
        MovieRental movieRental = new MovieRental(null, 3);
        assertEquals(movieRental.getDays(), 3);
    }
}