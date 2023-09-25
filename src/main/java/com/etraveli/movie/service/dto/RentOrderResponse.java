package com.etraveli.movie.service.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;

/**
 *This record use as rental order Response DTO.
 */
@Builder
public record RentOrderResponse(
        Customer customer,
        List<RentOrderLine> rentOrderLineList,
        BigDecimal totalRental,
        int frequentEnterPoints) {
}