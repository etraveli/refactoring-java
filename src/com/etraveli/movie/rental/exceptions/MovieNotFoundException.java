package com.etraveli.movie.rental.exceptions;

import java.util.NoSuchElementException;

public class MovieNotFoundException extends NoSuchElementException {
    public MovieNotFoundException(String movieId) {
        super(String.format("No such element is found in the movie list with given movieId %s", movieId));
    }

}
