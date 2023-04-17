package com.movie.rental.app.repository;

import com.movie.rental.app.model.Movie;
import com.movie.rental.app.enums.MovieCode;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This RentalInfoDataProvider class is used to provide necessary data
 * Note: This can be enhanced to provide data from a data source like database or a file
 *
 * @author pabasara8857@gmail.com
 */
@Component
public class RentalInfoDataProvider {

  /** Provide all movie data */
  public List<Movie> getMovies() {
    return List.of(
        new Movie("F001", "You've Got Mail", MovieCode.REGULAR),
        new Movie("F002", "Matrix", MovieCode.REGULAR),
        new Movie("F003", "Cars", MovieCode.CHILDREN),
        new Movie("F004", "Fast & Furious X", MovieCode.NEW));
  }
}
