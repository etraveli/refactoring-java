package com.mithwick93.refactoring.java.repositroy;

import com.mithwick93.refactoring.java.entity.Movie;
import com.mithwick93.refactoring.java.entity.MovieCode;

import java.util.Map;

/**
 * MovieRepository class to represent a repository of movies
 */
public class MovieRepository {
    private final Map<String, Movie> movies;

    /**
     * Constructor to initialize the repository with some movies
     */
    public MovieRepository() {
        this.movies = initializeMovies();
    }

    /**
     * Get a movie by its id
     *
     * @param movieId id of the movie
     * @return Movie with the given id
     */
    public Movie getMovie(String movieId) {
        return movies.get(movieId);
    }

    /**
     * Get all movies
     *
     * @return Map of all movies
     */
    public Map<String, Movie> getMovies() {
        return movies;
    }

    /**
     * Initialize the repository with some movies
     *
     * @return Map of movies
     */
    private Map<String, Movie> initializeMovies() {
        return Map.of(
                "F001", new Movie("You've Got Mail", MovieCode.REGULAR),
                "F002", new Movie("Matrix", MovieCode.REGULAR),
                "F003", new Movie("Cars", MovieCode.CHILDREN),
                "F004", new Movie("Fast & Furious X", MovieCode.NEW_RELEASE)
        );
    }

}
