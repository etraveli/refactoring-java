package com.rentalmovies.movie.service;

import com.rentalmovies.movie.model.MovieResponseDTO;
import com.rentalmovies.movie.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService
{
    MovieResponseDTO saveMovie(Movie movie);

    Optional<MovieResponseDTO> getMovieById(String id);

    List<MovieResponseDTO> getAllMovies();
}
