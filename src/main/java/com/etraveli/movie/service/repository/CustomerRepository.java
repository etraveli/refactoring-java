package com.etraveli.movie.service.repository;

import com.etraveli.movie.service.application.DataHolder;
import com.etraveli.movie.service.dto.Customer;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomerRepository {

    private final DataHolder dataHolder;

    public Customer findCustomerByID(String id) {
        return getSampleCustomerData().stream()
                .filter(customer -> customer.customerID().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No customer found with ID: " + id));
    }

    // call the data holder and retrieve sample data
    private List<Customer> getSampleCustomerData() {
        return dataHolder.getCustomers();
    }
}