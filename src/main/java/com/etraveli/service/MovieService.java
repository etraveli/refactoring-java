package com.etraveli.service;

import com.etraveli.modal.Movie;
import com.etraveli.modal.request.MovieRequest;
import com.etraveli.modal.response.MovieResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    MovieResponse createMovie(MovieRequest movieRequest);

    MovieResponse updateMovie(MovieRequest movieRequest);

    MovieResponse getMovieByMovieCode(String movieCode);
    Movie findMovieByMovieCode(String movieCode);

    List<MovieResponse> getAllMovies();

}

