package com.etraveli.movie.rental.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class MovieRentalRequest {

    @NotBlank(message = "Customer Id can not be empty")
    private String customerId;

    @NotNull(message = "Rental lines can not be null")
    @NotEmpty(message = "Rental lines can not be empty")
    @Valid
    private List<MovieRentalRequestLine> rentalLines;

}
