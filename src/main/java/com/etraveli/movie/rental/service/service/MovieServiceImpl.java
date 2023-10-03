package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.Movie;
import com.etraveli.movie.rental.service.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Movie not found"));
    }
}
