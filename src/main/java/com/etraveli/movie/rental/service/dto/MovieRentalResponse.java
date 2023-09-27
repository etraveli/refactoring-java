package com.etraveli.movie.rental.service.dto;

import lombok.Builder;
import java.math.BigDecimal;
import java.util.List;

@Builder
public record MovieRentalResponse(
        Customer customer,
        List<MovieRentalResponseLine> movieRentalResponseLines,
        BigDecimal totalRental,
        int frequentEnterPoints) {
}
