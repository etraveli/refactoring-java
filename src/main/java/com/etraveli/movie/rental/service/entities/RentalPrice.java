package com.etraveli.movie.rental.service.entities;

import java.math.BigDecimal;

public record RentalPrice(
        String movieGenre,
        BigDecimal basePrice,
        int noOfDaysForBasePrice,
        BigDecimal perDayPrice
) {
}
