package com.etraveli.repository;

import com.etraveli.modal.MovieRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRentalRepository extends JpaRepository<MovieRental, Long> {

}
