package com.etraveli.movie.rental.service.repositories;

import com.etraveli.movie.rental.service.entities.MovieRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRentalRepository extends JpaRepository<MovieRental, Long> {
    List<MovieRental> findByCustomerId(Long customerId);
}
