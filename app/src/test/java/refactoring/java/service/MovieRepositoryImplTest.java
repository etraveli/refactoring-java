package refactoring.java.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryImplTest {

    @Test
    void findById() {
        MovieRepositoryImpl movies = new MovieRepositoryImpl();

        assertNotNull(movies.findById("F001"));
        assertEquals("You've Got Mail", movies.findById("F001").getTitle());
    }

    @Test
    void findByIdFail() {
        MovieRepositoryImpl movies = new MovieRepositoryImpl();

        assertNull(movies.findById("X123"));
    }
}