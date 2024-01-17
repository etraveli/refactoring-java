package com.etraveli.repository;

import com.etraveli.model.Customer;
import com.etraveli.model.MovieRental;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InitialCustomerRepository implements CustomerRepository{
    HashMap<String, Customer> customers = new HashMap<>();

    public InitialCustomerRepository(){
        customers.put("C001", new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));
        customers.put("C002", new Customer("Children Customer", Arrays.asList(new MovieRental("F003", 7), new MovieRental("F003", 1))));
        customers.put("C003", new Customer("News Customer", Arrays.asList(new MovieRental("F004", 1), new MovieRental("F004", 3))));
        customers.put("C004", new Customer("Mix Customer", Arrays.asList(new MovieRental("F001", 1), new MovieRental("F002", 2),
                new MovieRental("F003", 3), new MovieRental("F004", 4))));
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customers.get(customerId);
    }
}
