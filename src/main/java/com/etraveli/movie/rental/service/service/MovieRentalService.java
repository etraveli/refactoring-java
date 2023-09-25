package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.dto.MovieRentalRequest;
import com.etraveli.movie.rental.service.dto.MovieRentalResponse;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MovieRentalService {

    public MovieRentalResponse getMovieRentalResponse(MovieRentalRequest request) {
        return MovieRentalResponse.builder()
                .customerName(null)
                .orderResponseLines(null)
                .totalRental(BigDecimal.ZERO)
                .build();
    }
}
