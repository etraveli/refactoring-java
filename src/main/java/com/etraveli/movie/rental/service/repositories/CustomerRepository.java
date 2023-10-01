package com.etraveli.movie.rental.service.repositories;

import com.etraveli.movie.rental.service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findById(Long customerId);
}
