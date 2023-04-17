package com.movie.rental.app.repository;

import com.movie.rental.app.model.Customer;
import com.movie.rental.app.model.Movie;
import com.movie.rental.app.model.MovieRental;
import com.movie.rental.app.enums.MovieCode;

import java.util.List;

/**
 * This DataProvider class is used to provide data.
 * Note: This can be enhanced to provide data from a data source like database or a flat file
 *
 * @author pabasara8857@gmail.com
 */
public class DataProvider {

  /** Provide all movie data */
  public static List<Movie> getMovies() {
    return List.of(
        new Movie("F001", "You've Got Mail", MovieCode.REGULAR),
        new Movie("F002", "Matrix", MovieCode.REGULAR),
        new Movie("F003", "Cars", MovieCode.CHILDREN),
        new Movie("F004", "Fast & Furious X", MovieCode.NEW));
  }

  /** Provide movie rental data */
  public static List<List<MovieRental>> getMovieRentals() {
    return List.of(List.of(new MovieRental("F001", 3), new MovieRental("F002", 1)));
  }

  /** Provide customer data */
  public static List<Customer> getCustomers() {
    return List.of(new Customer("C. U. Stomer", getMovieRentals().get(0)));
  }
}
