package com.show.movierental.repository;

import com.show.movierental.core.Movie;

import java.util.Map;

public interface MovieRepository {
    Movie findMovieById(String movieId);

    Map<String, Movie> getMovies();
}