package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.Movie;
import com.etraveli.movie.rental.service.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieByMovieId(Long movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }
}
