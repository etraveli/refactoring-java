package com.etraveli.repository;

import com.etraveli.modal.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByCustomerId(Long customerId);

    Customer findCustomerByIdNumber(String idNumber);
}
