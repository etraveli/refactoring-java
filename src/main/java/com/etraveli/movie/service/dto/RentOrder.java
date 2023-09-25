package com.etraveli.movie.service.dto;

import java.util.List;
import lombok.Builder;

/**
 *This record use as rental order request DTO.
 */
@Builder
public record RentOrder(
        String customerID,
        List<RentOrderLine> orderLineList) {
}