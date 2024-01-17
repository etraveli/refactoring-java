package com.etraveli.repository;

import com.etraveli.model.Movie;

public interface MovieRepository {
    Movie getMovieById(String movieId);
}
