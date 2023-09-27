package com.etraveli.movie.rental.service.repository;

import com.etraveli.movie.rental.service.dto.Customer;
import com.etraveli.movie.rental.service.util.SampleData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final SampleData sampleData;

    public Customer findCustomerById(String customerId) {
        return sampleData.getCustomerData().stream()
                .filter(customer -> customer.customerId().equals(customerId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Customer not found with customer id: " + customerId));
    }
}