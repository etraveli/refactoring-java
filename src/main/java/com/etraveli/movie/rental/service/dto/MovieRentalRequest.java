package com.etraveli.movie.rental.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieRentalRequest {
    private String customerId;
    private List<MovieRentalRequestLine> rentalLines;

}
