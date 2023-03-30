package com.junjie.movie.rental.repository;

import com.junjie.movie.rental.entity.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    public List<Movie> findByName(String name);
}
