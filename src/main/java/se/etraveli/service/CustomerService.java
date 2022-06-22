package se.etraveli.service;

import se.etraveli.entity.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer findCustomerByName(String name);
}
