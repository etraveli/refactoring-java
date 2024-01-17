package com.etraveli.repository;

import com.etraveli.model.Movie;
import com.etraveli.model.MovieCode;

import java.util.HashMap;

public class InitialMovieRepository implements MovieRepository {
    private final HashMap<String, Movie> movies = new HashMap<>();

    public InitialMovieRepository()
    {
        movies.put("F001", new Movie("You've Got Mail",MovieCode.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieCode.REGULAR));
        movies.put("F003", new Movie("Cars", MovieCode.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieCode.NEW));
    }

    @Override
    public Movie getMovieById(String movieId) {
        return movies.get(movieId);
    }
}
