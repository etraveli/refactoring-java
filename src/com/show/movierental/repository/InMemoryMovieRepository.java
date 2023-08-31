package com.show.movierental.repository;

import com.show.movierental.core.pricing.ChildrensPriceStrategy;
import com.show.movierental.core.Movie;
import com.show.movierental.core.pricing.NewReleasePriceStrategy;
import com.show.movierental.core.pricing.RegularPriceStrategy;

import java.util.HashMap;
import java.util.Map;

public class InMemoryMovieRepository implements MovieRepository {
    private Map<String, Movie> movieDatabase;

    public InMemoryMovieRepository() {
        movieDatabase = new HashMap<>();
        movieDatabase.put("F001", new Movie("You've Got Mail", new RegularPriceStrategy()));
        movieDatabase.put("F002", new Movie("Matrix", new RegularPriceStrategy()));
        movieDatabase.put("F003", new Movie("Cars", new ChildrensPriceStrategy()));
        movieDatabase.put("F004", new Movie("Fast & Furious X", new NewReleasePriceStrategy()));
    }

    public Movie findMovieById(String movieId) {
        return movieDatabase.get(movieId);
    }

    @Override
    public Map<String, Movie> getMovies() {
        return movieDatabase;
    }
}