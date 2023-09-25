package com.etraveli.movie.rental.service.dto;

import lombok.Builder;

@Builder
public record MovieRentalRequestLine(
        String movieId,
        int noOfDays
) { }

