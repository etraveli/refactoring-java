package com.etraveli.movie.rental.service.commons;

import com.etraveli.movie.rental.service.entities.Customer;
import com.etraveli.movie.rental.service.response.RentalDetailsResponse;
import com.etraveli.movie.rental.service.response.RentalLineDetailsResponse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CustomerCommon {
    public static List<Customer> createCustomerList() {
        return List.of(
                new Customer(1L, "John"),
                new Customer(2L, "Harry"));
    }

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
}
