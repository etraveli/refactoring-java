package com.etraveli.movie.service.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record RentOrder(
        String customerID,
        List<RentOrderLine> orderLineList) {
}