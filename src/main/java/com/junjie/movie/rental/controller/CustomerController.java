package com.junjie.movie.rental.controller;

import com.junjie.movie.rental.dto.CustomerDto;
import com.junjie.movie.rental.dto.converter.CustomerConverter;
import com.junjie.movie.rental.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public CustomerDto getCustomerById(@PathVariable("customerId") Long customerId) {
        return CustomerConverter.convertModel2Dto(customerService.findById(customerId));
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers() {
        var list = new ArrayList<CustomerDto>();
        customerService.findAll().stream().forEach(customer ->
                list.add(CustomerConverter.convertModel2Dto(customer)));
        return list;
    }

    @PostMapping
    public CustomerDto createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return CustomerConverter.convertModel2Dto(
                customerService.createCustomer(CustomerConverter.convertDto2Model(customerDto)));
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}
