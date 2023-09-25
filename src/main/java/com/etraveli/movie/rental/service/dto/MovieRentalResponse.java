package com.etraveli.movie.rental.service.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record MovieRentalResponse(
        String customerName,
        List<MovieRentalResponseLine> orderResponseLines,
        BigDecimal totalRental,
        int frequentEnterPoints)
{ }
