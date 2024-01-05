package com.etraveli.service.impl;

import com.etraveli.exceptiondomain.AlreadyExistException;
import com.etraveli.exceptiondomain.DataNotFoundException;
import com.etraveli.exceptiondomain.RequestNotValidException;
import com.etraveli.exceptiondomain.constant.ExceptionConstant;
import com.etraveli.modal.Customer;
import com.etraveli.modal.request.CustomerRequest;
import com.etraveli.modal.response.CustomerResponse;
import com.etraveli.repository.CustomerRepository;
import com.etraveli.service.CustomerService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        validateCustomer(customerRequest);
        Customer customer = this.mapRequestToEntity(customerRequest);
        Customer saveCustomer = this.customerRepository.save(customer);

        return this.mapEntityToResponse(saveCustomer);
    }

    private void validateCustomer(CustomerRequest customerRequest) {
        if (customerRequest != null && customerRequest.getIdNumber() != null){
            Customer customer = this.customerRepository.findCustomerByIdNumber(customerRequest.getIdNumber());

            if (customer != null){
                throw new AlreadyExistException(ExceptionConstant.CUSTOMER_ALREADY_EXIST);
            }
        }
    }

    @Override
    public Customer getCustomerByCustomerId(Long customerId) {
        return this.customerRepository.findCustomerByCustomerId(customerId);
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        if (customerRequest == null || customerRequest.getCustomerId() == null) {
            logger.error("--ERROR--CustomerServiceImpl--updateCustomer -- {}", ExceptionConstant.REQUEST_CANNOT_EMPTY);
            throw new RequestNotValidException(ExceptionConstant.REQUEST_CANNOT_EMPTY);
        }

        Customer customer = this.customerRepository.findCustomerByCustomerId(customerRequest.getCustomerId());

        if (customer == null) {
            logger.error("--ERROR--CustomerServiceImpl--updateCustomer--{}", ExceptionConstant.CUSTOMER_NOT_FOUND);
            throw new DataNotFoundException(ExceptionConstant.CUSTOMER_NOT_FOUND);
        }

        updateCustomerDetails(customer, customerRequest);

        Customer savedCustomer = this.customerRepository.save(customer);
        CustomerResponse customerResponse = mapEntityToResponse(savedCustomer);

        logger.info("--EXIT--CustomerServiceImpl--updateBookmark--");
        return customerResponse;
    }

    private void updateCustomerDetails(Customer customer, CustomerRequest customerRequest) {
        logger.info("--ENTER-- updateCustomerDetails --");
        if (customerRequest.getName() != null) {
            customer.setName(customerRequest.getName());
        }

        if (customerRequest.getIdNumber() != null) {
            customer.setIdNumber(customerRequest.getIdNumber());
        }

        if (customerRequest.getBirthYear() != null) {
            customer.setBirthYear(customerRequest.getBirthYear());
        }
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {

        Customer customer = this.customerRepository.findCustomerByCustomerId(customerId);
        if (customer == null) {
            logger.info("--ERROR--[GET]--DataNotFoundException--{}", ExceptionConstant.CUSTOMER_NOT_FOUND);
            throw new DataNotFoundException(ExceptionConstant.CUSTOMER_NOT_FOUND);
        }
        return mapEntityToResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customerList = this.customerRepository.findAll();

        return customerList.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    private Customer mapRequestToEntity(CustomerRequest customerRequest) {
        return modelMapper.map(customerRequest, Customer.class);
    }

    private CustomerResponse mapEntityToResponse(Customer customer) {
        return modelMapper.map(customer, CustomerResponse.class);
    }
}
