package com.etraveli.refactoring.repository;

import com.etraveli.refactoring.model.*;
import java.util.*;

public class CustomerDB {
    private Map<String, Customer> customers;

    public CustomerDB(){
        customers = new HashMap<String,Customer>();
    }
    public Customer create(String key, String name){
        Customer customer = new Customer(name);
        customers.put(key, customer);
        return customer;
    }
    
    public Customer get(String key){
        return customers.get(key);
    }
}
