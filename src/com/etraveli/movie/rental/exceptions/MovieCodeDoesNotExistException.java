package com.etraveli.movie.rental.exceptions;

import com.etraveli.movie.rental.models.Movie;

public class MovieCodeDoesNotExistException extends RuntimeException {
    public MovieCodeDoesNotExistException(Movie.MovieCode movieCode) {
        super(String.format("Invalid movie code: %s", movieCode));
    }
}
