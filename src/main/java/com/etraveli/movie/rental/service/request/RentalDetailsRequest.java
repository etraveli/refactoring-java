package com.etraveli.movie.rental.service.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RentalDetailsRequest {
    @NotNull
    private Long customerId;

    @NotNull
    @NotEmpty
    private List<RentalLineDetailsRequest> rentalDetailsRequestList;
}

