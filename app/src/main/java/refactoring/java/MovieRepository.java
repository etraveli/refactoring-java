package refactoring.java;

import refactoring.java.model.Movie;
import refactoring.java.model.MovieCategory;

import java.util.HashMap;

public class MovieRepository {
    HashMap<String, Movie> movies = new HashMap<>();

    public MovieRepository() {
        movies.put("F001", new Movie("You've Got Mail", MovieCategory.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieCategory.REGULAR));
        movies.put("F003", new Movie("Cars", MovieCategory.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieCategory.NEW));
    }

    public Movie findById(String id) {
        return movies.get(id);
    }
}
