package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.Customer;
import com.etraveli.movie.rental.service.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByCustomerId(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public void addLoyaltyPoints(Customer customer, int loyaltyPoints) {
        customer.setLoyaltyPoints(customer.getLoyaltyPoints() + loyaltyPoints);
        customerRepository.save(customer);
    }
}
