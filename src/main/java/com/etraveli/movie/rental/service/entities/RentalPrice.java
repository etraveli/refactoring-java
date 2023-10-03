package com.etraveli.movie.rental.service.entities;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RentalPrice(
        String movieGenre,
        BigDecimal basePrice,
        int noOfDaysForBasePrice,
        BigDecimal perDayPrice
) {
}
