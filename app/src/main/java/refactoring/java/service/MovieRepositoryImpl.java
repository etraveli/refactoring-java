package refactoring.java.service;

import refactoring.java.model.Movie;
import refactoring.java.model.MovieCategory;

import java.util.HashMap;

/**
 * Implements a simple "database" of our movies.
 */
public class MovieRepositoryImpl implements MovieRepository {
    HashMap<String, Movie> movies = new HashMap<>();

    public MovieRepositoryImpl() {
        movies.put("F001", new Movie("You've Got Mail", MovieCategory.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieCategory.REGULAR));
        movies.put("F003", new Movie("Cars", MovieCategory.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieCategory.NEW));
    }

    @Override
    public Movie findById(String id) {
        return movies.get(id);
    }
}
