package com.etraveli.db.repository;

import com.etraveli.db.entity.MovieRentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRentalRepository extends JpaRepository<MovieRentalEntity, String> {
}
