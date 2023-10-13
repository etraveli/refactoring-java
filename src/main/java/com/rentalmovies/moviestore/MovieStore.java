package com.rentalmovies.moviestore;

import com.rentalmovies.exceptions.MovieNotFoundException;
import com.rentalmovies.models.Movie;
import com.rentalmovies.rentalpricingstrategy.ChildrenMovie;
import com.rentalmovies.rentalpricingstrategy.NewReleaseMovie;
import com.rentalmovies.rentalpricingstrategy.RegularMovie;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Extracted the logic of storing and retrieving movies to
 * a new MovieStore class to decouple it from RentalInfo.
 *
 */
public final class MovieStore
{
    private final Map<String, Movie> movies = new HashMap<>();

    public MovieStore()
    {
        movies.put("F001", new Movie("You've Got Mail", new RegularMovie()));
        movies.put("F002", new Movie("Matrix", new RegularMovie()));
        movies.put("F003", new Movie("Cars", new ChildrenMovie()));
        movies.put("F004", new Movie("Fast & Furious X", new NewReleaseMovie()));
    }

    public Movie getMovie(String movieId)
    {
        return Optional.ofNullable(movies.get(movieId))
                .orElseThrow(() -> new MovieNotFoundException(movieId));
    }
}
