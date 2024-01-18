package com.etraveli.repository;

import com.etraveli.model.Movie;

public interface MovieRepository {
    /**
     * Gets Movie associated with provided movieId
     *
     * @param movieId textual identifier for which the Movie should be returned
     * @return movie associated with requested movie id
     */
    Movie getMovieById(String movieId);
}
