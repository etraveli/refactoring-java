package com.etraveli.repository;

import com.etraveli.modal.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findMovieByMovieId(Long movieId);

    Movie findMovieByMovieCode(String movieCode);
}
