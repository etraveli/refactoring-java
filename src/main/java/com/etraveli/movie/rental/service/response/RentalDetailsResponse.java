package com.etraveli.movie.rental.service.response;

import com.etraveli.movie.rental.service.entities.Customer;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record RentalDetailsResponse(
        Customer customer,
        List<RentalLineDetailsResponse> rentalLineDetailsResponse,
        BigDecimal totalRent,
        int frequentEnterPoints
) {
}
