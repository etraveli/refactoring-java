package com.mithwick93.refactoring.java.repositroy;

import com.mithwick93.refactoring.java.entity.Movie;

import java.util.Map;

/**
 * MovieRepository interface to represent a repository of movies
 */
public interface MovieRepository {

    /**
     * Get a movie by its id
     *
     * @param movieId id of the movie
     * @return Movie with the given id
     */
    Movie getMovie(String movieId);

    /**
     * Get all movies
     *
     * @return Map of all movies
     */
    Map<String, Movie> getMovies();
}
