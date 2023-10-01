package com.etraveli.movie.rental.service.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RentalLineDetailsResponse(
        String movieName,
        BigDecimal rent,
        int frequentEnterPoints
) {
}
