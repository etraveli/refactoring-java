package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.Movie;
import com.etraveli.movie.rental.service.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
