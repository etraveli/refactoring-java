package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerByCustomerId(Long customerId);
    void addLoyaltyPoints(Customer customer, int loyaltyPoints);
}
