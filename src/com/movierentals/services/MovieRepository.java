package com.movierentals.services;


import com.movierentals.domain.Movie;

import java.util.HashMap;
import java.util.Map;

import static com.movierentals.domain.MovieCategory.*;


public class MovieRepository {

    private static final Map<String, Movie> movies = createAMoviesRepo();


    private static Map<String, Movie> createAMoviesRepo() {
        Map<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("You've Got Mail", REGULAR));
        movies.put("F002", new Movie("Matrix", REGULAR));
        movies.put("F003", new Movie("Cars", CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", NEW));
        return movies;
    }

    public Movie findById(String id) {
        return movies.get(id);
    }
}
