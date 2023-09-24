package com.etraveli.movie.service.dto;

import lombok.Builder;

@Builder
public record Customer(
        String customerID,
        String customerName) {
}
