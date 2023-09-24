package com.etraveli.movie.service.dto;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record RentalFee(
        BigDecimal flatFee,
        int flatFeeLimit,
        BigDecimal dailyFee) {
}