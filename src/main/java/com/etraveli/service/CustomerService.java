package com.etraveli.service;

import com.etraveli.modal.Customer;
import com.etraveli.modal.request.CustomerRequest;
import com.etraveli.modal.response.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomerById(Long customerId);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse updateCustomer(CustomerRequest customerRequest);
}

