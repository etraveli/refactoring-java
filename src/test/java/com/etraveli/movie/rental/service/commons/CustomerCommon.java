package com.etraveli.movie.rental.service.commons;

import com.etraveli.movie.rental.service.entities.Customer;
import com.etraveli.movie.rental.service.request.RentalDetailsRequest;
import com.etraveli.movie.rental.service.request.RentalLineDetailsRequest;
import com.etraveli.movie.rental.service.response.RentalDetailsResponse;
import com.etraveli.movie.rental.service.response.RentalLineDetailsResponse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CustomerCommon {

    public static Customer createCustomerById(Long customerId) {
        return new Customer(customerId, "John");
    }

    public static RentalDetailsResponse createMovieRentalResponse() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John");

        List<RentalLineDetailsResponse> responseLines = Arrays.asList(
                RentalLineDetailsResponse.builder()
                        .movieName("SuperMan")
                        .rent(BigDecimal.valueOf(2))
                        .frequentEnterPoints(2)
                        .build()
        );

        return RentalDetailsResponse.builder()
                .customer(customer)
                .rentalLineDetailsResponse(responseLines)
                .totalRent(BigDecimal.valueOf(2))
                .frequentEnterPoints(2)
                .build();
    }

    public static RentalDetailsRequest createMovieRentalRequest(Long customerId) {
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
