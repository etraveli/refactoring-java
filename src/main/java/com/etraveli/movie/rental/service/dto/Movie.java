package com.etraveli.movie.rental.service.dto;

import com.etraveli.movie.rental.service.configuration.MovieRentalConfiguration.MovieCode;
import lombok.Builder;

@Builder
public record Movie(
        String movieId,
        String title,
        MovieCode movieCode
) {
}
