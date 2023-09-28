package com.etraveli.movie.rental.service.util;

import com.etraveli.movie.rental.service.dto.Customer;

import java.util.List;

public class CustomerFixture {

    public static List<Customer> createCustomerList() {
        return List.of(
                new Customer("1", "C. U. Stomer"),
                new Customer("2", "W. Peter"));
    }

    public static Customer createCustomerById(String customerId) {
        return new Customer(customerId, "C. U. Stomer");
    }
}
