package refactoring.java.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void getTitle() {
        Movie movie = new Movie("The Godfather", null);
        assertEquals("The Godfather", movie.getTitle());
    }

    @Test
    void getCategory() {
        Movie movie = new Movie(null, MovieCategory.REGULAR);
        assertEquals(MovieCategory.REGULAR, movie.getCategory());
    }
}