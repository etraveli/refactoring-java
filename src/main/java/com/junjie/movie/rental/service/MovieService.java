package com.junjie.movie.rental.service;

import com.google.common.collect.Lists;
import com.junjie.movie.rental.entity.Movie;
import com.junjie.movie.rental.exception.ResourceNotFoundException;
import com.junjie.movie.rental.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public Movie findMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("the movieId %s does not exist!", movieId)));
    }

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return Lists.newArrayList(movieRepository.findAll());
    }

    @Transactional
    public void deleteMovieById(Long movieId) {
        movieRepository.deleteById(movieId);
    }
}
