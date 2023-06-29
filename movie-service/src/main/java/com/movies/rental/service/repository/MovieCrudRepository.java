package com.movies.rental.service.repository;

import com.movies.rental.service.repository.entity.Movie;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCrudRepository extends MongoRepository<Movie,String> {

  Movie findByMovieId(String movieId);

  List<Movie> findAll();

  void deleteAll();
}
