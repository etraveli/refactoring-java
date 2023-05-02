package com.etraveli.app.movie.rental.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Movie Repository functions
 */
class MovieRepositoryTest {

    private MovieRepository movieRepository;
    @BeforeEach
    void setUp() {
        movieRepository = new MovieRepository();
    }

    @AfterEach
    void tearDown() {
        movieRepository = null;
    }

    @Test
    @DisplayName("Find a valid movie by Id")
    void findMovieById() {
        assertNotNull(movieRepository.findMovieById("F001"));
    }

    @Test
    @DisplayName("Cannot find an unavailable movie by an invalid Id")
    void findMovieByInvalidId() {
        assertNull(movieRepository.findMovieById("F0000"));
    }
}