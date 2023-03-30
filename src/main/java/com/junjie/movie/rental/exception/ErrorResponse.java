package com.junjie.movie.rental.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private Integer status;
    private HttpStatus code;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(HttpStatus code, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = code.value();
        this.code = code;
        this.message = message;
    }
}