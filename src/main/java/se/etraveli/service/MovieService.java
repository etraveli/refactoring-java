package se.etraveli.service;

import se.etraveli.entity.Movie;

public interface MovieService {
    Movie getMovieByCode(String code);
}
