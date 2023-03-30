package com.junjie.movie.rental.repository;

import com.junjie.movie.rental.entity.Customer;
import com.junjie.movie.rental.entity.MovieRentalOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRentalOrderRepository extends CrudRepository<MovieRentalOrder, Long> {
    public List<MovieRentalOrder> findByCustomer(Customer customer);

}
