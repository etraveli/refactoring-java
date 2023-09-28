package com.etraveli.movie.rental.service.dto;

import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record MovieRentalResponseLine(
        String movieName,
        BigDecimal rental,
        int frequentEnterPoints
) {
}