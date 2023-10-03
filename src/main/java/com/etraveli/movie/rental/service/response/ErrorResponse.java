package com.etraveli.movie.rental.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class will return custom error messages
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String status;
    private Object description;
}
