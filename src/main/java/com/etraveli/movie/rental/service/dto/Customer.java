package com.etraveli.movie.rental.service.dto;

import lombok.Builder;

@Builder
public record Customer (
        String customerId,
        String name
){ }
