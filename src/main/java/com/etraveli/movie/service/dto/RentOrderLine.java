package com.etraveli.movie.service.dto;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record RentOrderLine(
        String movieCode,
        String movieName,
        int rentDays,
        BigDecimal rentAmount) {

    public RentOrderLine {
        if (rentDays <= 0) {
            throw new IllegalArgumentException("value should be greater than 0 : rentDays");
        }
    }
}