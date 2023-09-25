package com.etraveli.movie.service.dto;

import java.math.BigDecimal;
import lombok.Builder;

/**
 *This record use as a rental fee DTO.
 */
@Builder
public record RentalFee(
        BigDecimal flatFee,
        int flatFeeLimit,
        BigDecimal dailyFee) {
}