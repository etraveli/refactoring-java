package com.junjie.movie.rental.service;

import com.google.common.collect.Lists;
import com.junjie.movie.rental.entity.Customer;
import com.junjie.movie.rental.exception.ResourceNotFoundException;
import com.junjie.movie.rental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("the customerId %s does not exist!", customerId)));
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return Lists.newArrayList(customerRepository.findAll());
    }

    @Transactional
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
