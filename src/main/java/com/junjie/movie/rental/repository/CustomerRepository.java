package com.junjie.movie.rental.repository;

import com.junjie.movie.rental.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public List<Customer> findByName(String name);
}
