package com.junjie.movie.rental.repository;

import com.junjie.movie.rental.entity.MovieRental;
import com.junjie.movie.rental.entity.MovieRentalOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRentalRepository extends CrudRepository<MovieRental, Long> {
    public List<MovieRental> findByMovieRentalOrder(MovieRentalOrder movieRentalOrder);
}
