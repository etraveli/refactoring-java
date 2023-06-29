package com.movies.rental.service;

import com.movies.rental.service.repository.CustomerCrudRepository;
import com.movies.rental.service.repository.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

  private final CustomerCrudRepository customerCrudRepository;

  public CustomerService(CustomerCrudRepository customerCrudRepository) {
    this.customerCrudRepository = customerCrudRepository;
  }

  public Customer fetchAll(String customerId) {
      return customerCrudRepository.findByCustomerId(customerId);
  }

  public void save(Customer customer) {
    customerCrudRepository.save(customer);
  }

  public void delete() {
    customerCrudRepository.deleteAll();
  }
}
