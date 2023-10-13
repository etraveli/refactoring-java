package com.rentalmovies.movierental.controller;

import com.rentalmovies.movierental.model.MovieRentalResponseDTO;
import com.rentalmovies.movierental.model.MovieRental;
import com.rentalmovies.movierental.service.MovieRentalService;

import java.util.Optional;

/**
 * Future Plan is to convert this application to REST based application.
 * The MovieRentalController class would be responsible for handling incoming requests related to MovieRental,
 * delegating the execution of these requests to the appropriate service layer for REST.
 * It can easily be adapted to a REST controller to handle HTTP requests.
 *
 * @author Sajid Riaz
 */
public final class MovieRentalController
{
    private final MovieRentalService movieRentalService;

    public MovieRentalController(final MovieRentalService customerService)
    {
        this.movieRentalService = customerService;
    }

    public MovieRentalResponseDTO saveMovieRental(MovieRental movieRental)
    {
        return movieRentalService.saveMovieRental(movieRental);
    }

    public Optional<MovieRentalResponseDTO> getMovieRentalById(Long id)
    {
        return movieRentalService.getMovieRentalById(id);
    }
}
