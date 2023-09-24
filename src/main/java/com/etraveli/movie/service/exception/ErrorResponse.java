package com.etraveli.movie.service.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String errorMessage,
        int errorCode) {
}
