package com.junjie.movie.rental.dto.converter;

import com.junjie.movie.rental.dto.MovieRentalDto;
import com.junjie.movie.rental.entity.MovieRental;

public class MovieRentalConverter {
    public static MovieRentalDto convertModel2Dto(MovieRental movieRental) {
        return new MovieRentalDto(movieRental.getMovie().getId(),
                movieRental.getRentDays(),
                movieRental.getExpense());
    }
}
