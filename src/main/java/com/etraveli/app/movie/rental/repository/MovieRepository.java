package com.etraveli.app.movie.rental.repository;

import com.etraveli.app.movie.rental.model.Movie;
import com.etraveli.app.movie.rental.model.MovieCategory;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores Movie Information
 */
public class MovieRepository {

    private Map<String, Movie> movies;

    public MovieRepository() {
        movies = new HashMap<String, Movie>();

        movies.put("F001", new Movie("You've Got Mail", MovieCategory.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieCategory.REGULAR));
        movies.put("F003", new Movie("Cars", MovieCategory.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieCategory.NEW));
    }

    public Movie findMovieById(String id) {
        return movies.get(id);
    }
}
