package com.rentalmovies.customer.controller;

import com.rentalmovies.customer.model.Customer;
import com.rentalmovies.customer.model.CustomerResponseDTO;
import com.rentalmovies.customer.service.CustomerService;

import java.util.Optional;

/**
 * Future Plan is to convert this application to REST based application.
 * The CustomerController class would be responsible for handling incoming requests related to customers,
 * delegating the execution of these requests to the appropriate service layer for REST.
 * It can easily be adapted to a REST controller to handle HTTP requests.
 *
 * @author Sajid Riaz
 */
public final class CustomerController
{
    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public CustomerResponseDTO saveCustomer(final Customer customer)
    {
        return customerService.saveCustomer(customer);
    }

    public Optional<CustomerResponseDTO> getCustomerById(final String id)
    {
        return customerService.getCustomerById(id);
    }
}
