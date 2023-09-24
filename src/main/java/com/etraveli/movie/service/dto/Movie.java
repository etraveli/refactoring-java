package com.etraveli.movie.service.dto;

import lombok.Builder;

@Builder
public record Movie(String movieCode,
                    String movieName,
                    MovieType type) {
}