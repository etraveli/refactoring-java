package com.mithwick93.refactoring.java.repositroy.impl;

import com.mithwick93.refactoring.java.entity.Movie;
import com.mithwick93.refactoring.java.repositroy.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MovieRepositoryImplTest {

    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository = new MovieRepositoryImpl();
    }

    @Test
    void givenMovieRepositoryInitialized_whenGetMovies_thenReturnMovieList() {
        Map<String, Movie> movies = movieRepository.getMovies();

        assertNotNull(movies);
        assertFalse(movies.isEmpty());
    }

    @Test
    void givenMovieRepositoryInitialized_whenGetMovie_thenReturnMovie() {
        String movieId = "F001";

        Movie movie = movieRepository.getMovie(movieId);

        assertNotNull(movie);
        assertNotNull(movie.title());
        assertNotNull(movie.code());
    }

    @Test
    void givenMovieRepositoryInitialized_whenGetMovieWithInvalidId_thenReturnNull() {
        String movieId = "F005";

        Movie movie = movieRepository.getMovie(movieId);

        assertNull(movie);
    }
}