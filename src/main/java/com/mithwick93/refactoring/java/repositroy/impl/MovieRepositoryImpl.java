package com.mithwick93.refactoring.java.repositroy.impl;

import com.mithwick93.refactoring.java.entity.Movie;
import com.mithwick93.refactoring.java.repositroy.MovieRepository;

import java.util.Map;

/**
 * MovieRepository class to represent a repository of movies.
 */
public class MovieRepositoryImpl implements MovieRepository {
    private final Map<String, Movie> movies;

    /**
     * Constructor to initialize the repository with some movies.
     */
    public MovieRepositoryImpl() {
        this.movies = initializeMovies();
    }

    /**
     * Get a movie by its id.
     *
     * @param movieId id of the movie
     * @return movie
     */
    @Override
    public Movie getMovie(final String movieId) {
        return movies.get(movieId);
    }

    /**
     * Get all movies.
     *
     * @return Map of movies
     */
    @Override
    public Map<String, Movie> getMovies() {
        return movies;
    }

    /**
     * Initialize the repository with some movies.
     *
     * @return Map of movies
     */
    private Map<String, Movie> initializeMovies() {
        return Map.of(
                "F001",
                new Movie("You've Got Mail", Movie.MovieCode.REGULAR),
                "F002",
                new Movie("Matrix", Movie.MovieCode.REGULAR),
                "F003",
                new Movie("Cars", Movie.MovieCode.CHILDREN),
                "F004",
                new Movie("Fast & Furious X", Movie.MovieCode.NEW_RELEASE)
        );
    }

}
