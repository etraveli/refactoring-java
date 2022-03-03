package services;

import enums.MovieCode;
import models.Movie;

import java.util.HashMap;

/**
 * service layer for Movie model
 */
public class MovieService {
    private static HashMap<String, Movie> movies = new HashMap<>();
    static {
        movies.put("F001", new Movie("You've Got Mail", MovieCode.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieCode.REGULAR));
        movies.put("F003", new Movie("Cars", MovieCode.CHILDREN));
        movies.put("F004", new Movie("Fast & Furious X", MovieCode.NEW));
    }

    public Movie getMovieByMovieId(String movieId){
        return movies.get(movieId);
    }
}
