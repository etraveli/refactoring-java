package com.rentalmovies.movierental.model;

import com.rentalmovies.movierental.model.MovieRental;

public class MovieRentalResponseDTO
{
    private final String movieId;
    private final int rentalDays;

    public MovieRentalResponseDTO(MovieRental rental)
    {
        this.movieId = rental.getMovieId();
        this.rentalDays = rental.getRentalDays();
    }

    public String getMovieId()
    {
        return movieId;
    }

    public int getRentalDays()
    {
        return rentalDays;
    }
    @Override
    public String toString()
    {
        return "MovieRentalResponseDTO{" +
                "movieId='" + movieId + '\'' +
                ", rentalDays=" + rentalDays +
                '}';
    }
}
