package com.movies.rental.grpc;

import com.movies.rental.service.CustomerService;
import com.movies.rental.service.repository.entity.Customer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping(
      value = "/customer/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Customer getCustomers(@PathVariable String id) {
    return customerService.fetchAll(id);
  }

  @PostMapping(
      value = "/customer",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> saveCustomer(@RequestBody Customer customer) {
    return ResponseEntity.ok().build();
  }
}
