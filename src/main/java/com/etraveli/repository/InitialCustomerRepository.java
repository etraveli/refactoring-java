package com.etraveli.repository;

import com.etraveli.model.Customer;
import com.etraveli.model.MovieRental;

import java.util.Arrays;
import java.util.HashMap;

public class InitialCustomerRepository implements CustomerRepository{
    HashMap<String, Customer> customers = new HashMap<>();

    public InitialCustomerRepository(){
        customers.put("C001", new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customers.get(customerId);
    }
}
