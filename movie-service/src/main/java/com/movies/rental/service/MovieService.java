package com.movies.rental.service;

import com.movies.rental.service.repository.MovieCrudRepository;
import com.movies.rental.service.repository.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieService {

  private final MovieCrudRepository movieCrudRepository;

  public MovieService(MovieCrudRepository movieCrudRepository) {
    this.movieCrudRepository = movieCrudRepository;
  }

  public Movie fetch(String movieId) {
    return movieCrudRepository.findByMovieId(movieId);
  }

    public List<Movie> fetchAll() {
    return movieCrudRepository.findAll();
    }
  public void save(Movie movie) {
    movieCrudRepository.save(movie);
  }

  public void delete() {
    movieCrudRepository.deleteAll();
  }
}
