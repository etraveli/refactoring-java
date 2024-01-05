package com.etraveli.controller;

import com.etraveli.modal.request.CustomerRequest;
import com.etraveli.modal.response.CustomerResponse;
import com.etraveli.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
    private final Logger logger = getLogger(this.getClass());
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public Mono<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        logger.info("--ENTER--[POST]--createCustomer--Request-- {}", customerRequest);

        CustomerResponse customerResponse = this.customerService.createCustomer(customerRequest);

        logger.info("--EXIT--[POST]--createCustomer--");
        return Mono.just(customerResponse);
    }

    @PutMapping("/customer")
    public Mono<CustomerResponse> updateCustomer(@RequestBody CustomerRequest customerRequest) {
        logger.info("--ENTER--[PUT]--updateCustomer--Request-- {}", customerRequest);

        CustomerResponse customerResponse = this.customerService.updateCustomer(customerRequest);

        logger.info("--EXIT--[PUT]--updateCustomer--");
        return Mono.just(customerResponse);
    }

    @GetMapping("/customer/{customerId}")
    public Mono<CustomerResponse> getCustomerById(@PathVariable Long customerId) {
        logger.info("--ENTER--[GET]--getCustomerById--{}", customerId);

        CustomerResponse customerResponse = this.customerService.getCustomerById(customerId);

        logger.info("--EXIT--[GET]--getCustomerById--");
        return Mono.just(customerResponse);
    }

    @GetMapping("/customers")
    public Flux<CustomerResponse> getAllCustomers() {
        logger.info("--ENTER--[GET]--getAllCustomers--");

        List<CustomerResponse> customerResponse = this.customerService.getAllCustomers();

        logger.info("--EXIT--[GET]--getAllCustomers--");
        return Flux.fromIterable(customerResponse);
    }
}
