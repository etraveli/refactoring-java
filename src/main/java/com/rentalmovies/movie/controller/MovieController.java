package com.rentalmovies.movie.controller;

import com.rentalmovies.movie.model.MovieResponseDTO;
import com.rentalmovies.movie.model.Movie;
import com.rentalmovies.movie.service.MovieService;

import java.util.Optional;

/**
 * Future Plan is to convert this application to REST based application.
 * The MovieController class would be responsible for handling incoming requests related to customers,
 * delegating the execution of these requests to the appropriate service layer for REST.
 * It can easily be adapted to a REST controller to handle HTTP requests.
 *
 * @author Sajid Riaz
 */
public class MovieController
{
    private final MovieService movieService;

    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    public MovieResponseDTO saveMovie(Movie movie)
    {
        return movieService.saveMovie(movie);
    }

    public Optional<MovieResponseDTO> getMovieById(String id)
    {
        return movieService.getMovieById(id);
    }
}
