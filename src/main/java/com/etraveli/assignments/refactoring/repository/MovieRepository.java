package com.etraveli.assignments.refactoring.repository;

import com.etraveli.assignments.refactoring.exception.MovieNotFoundException;
import com.etraveli.assignments.refactoring.model.Movie;
import com.etraveli.assignments.refactoring.util.MovieCategory;
import java.util.Map;

/** The Movie repository class that demonstrates getting details of movies. */
public class MovieRepository {
  private final Map<String, Movie> movies;

  /** Instantiates a new Movie repository. */
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

  /**
   * Gets movie.
   *
   * @param id the id
   * @return the movie
   */
  public Movie getMovie(String id) {
    var movie = movies.get(id);
    if (movie == null) {
      throw new MovieNotFoundException(String.format("Movie not found for Id %s", id));
    }
    return movie;
  }
}
