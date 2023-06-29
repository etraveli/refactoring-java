package com.movies.rental.service.repository;

import com.movies.rental.service.repository.entity.Customer;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCrudRepository extends MongoRepository<Customer,String> {
    Customer findByCustomerId(String id);

    void deleteAll();
}
