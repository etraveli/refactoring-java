package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.Customer;
import com.etraveli.movie.rental.service.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomerByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }
}
