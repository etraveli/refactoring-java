package repository.impl;

import model.Movie;
import model.RentalCategory;
import repository.MovieRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryMapMovieRepository implements MovieRepository {

    public static Map<String, Movie> movies = new HashMap<>();

    static {
        movies.put("F001", new Movie("F001", "You've Got Mail", RentalCategory.REGULAR));
        movies.put("F002", new Movie("F002", "Matrix", RentalCategory.REGULAR));
        movies.put("F003", new Movie("F003", "Cars", RentalCategory.CHILDRENS));
        movies.put("F004", new Movie("F004", "Fast & Furious X", RentalCategory.NEW));
    }

    @Override
    public Movie findById(String id) {
        return movies.get(id);
    }

    @Override
    public void add(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    @Override
    public boolean exists(String id) {
        return movies.containsKey(id);
    }
}
