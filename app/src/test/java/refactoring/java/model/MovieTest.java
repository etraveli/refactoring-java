package refactoring.java.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void getTitle() {
        Movie movie = new Movie("The Godfather", null);
        assertEquals(movie.getTitle(), "The Godfather");
    }

    @Test
    void getCode() {
        Movie movie = new Movie(null, "regular");
        assertEquals(movie.getCode(), "regular");
    }
}