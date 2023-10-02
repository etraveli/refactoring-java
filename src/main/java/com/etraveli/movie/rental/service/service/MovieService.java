package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.Movie;

public interface MovieService {
    Movie findById(Long id);
}
