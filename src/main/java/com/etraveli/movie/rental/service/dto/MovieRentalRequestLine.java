package com.etraveli.movie.rental.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MovieRentalRequestLine(
        @NotBlank(message = "Movie id can not be empty")
        String movieId,

        @Min(value = 1, message = "Rental days should be grater than 0")
        int noOfDays
) {
}

