package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerByCustomerId(Long customerId);
}
