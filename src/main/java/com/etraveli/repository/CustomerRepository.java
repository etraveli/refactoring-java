package com.etraveli.repository;

import com.etraveli.model.Customer;

public interface CustomerRepository {
    Customer getCustomerById(String customerId);
}
