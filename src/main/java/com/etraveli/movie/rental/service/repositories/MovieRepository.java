package com.etraveli.movie.rental.service.repositories;

import com.etraveli.movie.rental.service.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
