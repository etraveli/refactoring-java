package com.rentalmovies.customer.service;

import com.rentalmovies.customer.model.Customer;
import com.rentalmovies.customer.model.CustomerResponseDTO;
import com.rentalmovies.customer.repository.CustomerDao;

import java.util.Optional;

public class CustomerServiceImpl implements CustomerService
{
    private final CustomerDao customerDao;

    public CustomerServiceImpl(final CustomerDao customerDao)
    {
        this.customerDao = customerDao;
    }

    @Override
    public CustomerResponseDTO saveCustomer(final Customer customer)
    {
        Customer savedCustomer = customerDao.save(customer);
        return new CustomerResponseDTO(savedCustomer);
    }

    @Override
    public Optional<CustomerResponseDTO> getCustomerById(final String id)
    {
        Optional<Customer> customer = Optional.of(customerDao.findById(id));
        return customer.map(CustomerResponseDTO::new);
    }
}
