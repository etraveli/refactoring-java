package com.etraveli.movie.rental.service.commons;

import com.etraveli.movie.rental.service.entities.RentalPrice;
import com.etraveli.movie.rental.service.request.RentalDetailsRequest;
import com.etraveli.movie.rental.service.request.RentalLineDetailsRequest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class RentalDetailCommon {
    public static RentalDetailsRequest createRentalDetailRequest(Long customerId) {
        RentalDetailsRequest request = new RentalDetailsRequest();
        request.setCustomerId(customerId);

        List<RentalLineDetailsRequest> rentalRequestLines = Arrays.asList(
                new RentalLineDetailsRequest(1L, 2),
                new RentalLineDetailsRequest(2L, 1)
        );
        request.setRentalDetailsRequestList(rentalRequestLines);

        return request;
    }
}
