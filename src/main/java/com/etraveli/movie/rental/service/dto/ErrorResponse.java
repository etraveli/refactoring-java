package com.etraveli.movie.rental.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class is responsible for set custom error messages
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String status;
    private Object description;
}