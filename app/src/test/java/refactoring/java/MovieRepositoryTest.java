package refactoring.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

    @Test
    void findById() {
        MovieRepository movies = new MovieRepository();

        assertNotNull(movies.findById("F001"));
        assertEquals("You've Got Mail", movies.findById("F001").getTitle());
    }

    @Test
    void findByIdFail() {
        MovieRepository movies = new MovieRepository();

        assertNull(movies.findById("X123"));
    }
}