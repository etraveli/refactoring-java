package com.etraveli.assignments.refactoring.repository;

import com.etraveli.assignments.refactoring.model.Movie;
import com.etraveli.assignments.refactoring.util.MovieCategory;
import java.util.Map;

public class MovieRepository {
  private final Map<String, Movie> movies;

  public MovieRepository() {
    this.movies = getPreConfiguredMovies();
  }

  private Map<String, Movie> getPreConfiguredMovies() {
    return Map.of(
        "F001", new Movie("F001", "You've Got Mail", MovieCategory.REGULAR),
        "F002", new Movie("F002", "Matrix", MovieCategory.REGULAR),
        "F003", new Movie("F003", "Cars", MovieCategory.CHILDRENS),
        "F004", new Movie("F004", "Fast & Furious X", MovieCategory.NEW));
  }

  public Movie getMovie(String id) {
    return movies.get(id);
  }
}
