package com.etraveli.movie.rental.service.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RentalLineDetailsRequest(
        @NotBlank Long movieId,
        @Min(value = 1) int noOfDays) {
}
