package com.etraveli.movie.service.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;

@Builder
public record RentOrderResponse(
        Customer customer,
        List<RentOrderLine> rentOrderLineList,
        BigDecimal totalRental,
        int frequentEnterPoints) {
}