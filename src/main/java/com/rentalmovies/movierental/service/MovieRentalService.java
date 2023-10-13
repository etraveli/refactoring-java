package com.rentalmovies.movierental.service;

import com.rentalmovies.movierental.model.MovieRentalResponseDTO;
import com.rentalmovies.movierental.model.MovieRental;

import java.util.Optional;

public interface MovieRentalService
{
    MovieRentalResponseDTO saveMovieRental(MovieRental movieRental);

    Optional<MovieRentalResponseDTO> getMovieRentalById(Long id);
}
