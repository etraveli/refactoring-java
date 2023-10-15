package com.rentalmovies.movierental.service;

import com.rentalmovies.movierental.model.MovieRentalResponseDTO;
import com.rentalmovies.movierental.model.MovieRental;
import com.rentalmovies.movierental.repository.MovieRentalDao;

import java.util.Optional;

public class MovieRentalServiceImpl implements MovieRentalService
{
    private final MovieRentalDao movieRentalDao;

    public MovieRentalServiceImpl(MovieRentalDao movieRentalDao)
    {
        this.movieRentalDao = movieRentalDao;
    }

    @Override
    public MovieRentalResponseDTO saveMovieRental(MovieRental movieRental)
    {
        movieRentalDao.save(movieRental);
        return new MovieRentalResponseDTO(movieRental);
    }

    @Override
    public Optional<MovieRentalResponseDTO> getMovieRentalById(Long id)
    {
        MovieRental movieRental = movieRentalDao.findById(id);
        return Optional.ofNullable(movieRental)
                .map(MovieRentalResponseDTO::new);
    }
}
