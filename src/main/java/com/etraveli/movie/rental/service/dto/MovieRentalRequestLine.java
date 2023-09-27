package com.etraveli.movie.rental.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MovieRentalRequestLine(
        @NotNull(message = "Movie id can not be null")
        @NotEmpty(message = "Movie id can not be empty")
        @Valid
        String movieId,

        @Min(value = 1, message = "Rental days should be grater than 0")
        @Valid
        int noOfDays
) {
}

