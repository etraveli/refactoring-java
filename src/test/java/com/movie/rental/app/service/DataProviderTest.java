package com.movie.rental.app.service;

import com.movie.rental.app.enums.MovieCode;
import com.movie.rental.app.model.Customer;
import com.movie.rental.app.model.Movie;
import com.movie.rental.app.model.MovieRental;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

/**
 * This DataProviderTest class is used to provide test data.
 *
 * @author pabasara8857@gmail.com
 */
public class DataProviderTest {

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
    return List.of(
        List.of(new MovieRental("F001", 1)),
        List.of(new MovieRental("F001", 4)),
        List.of(new MovieRental("F004", 5)),
        List.of(new MovieRental("F003", 2)),
        List.of(new MovieRental("F003", 6)),
        List.of(new MovieRental("F001", 3), new MovieRental("F002", 1)),
        List.of(
            new MovieRental("F001", 2), new MovieRental("F003", 3), new MovieRental("F004", 6)));
  }

  /** Provide customer data */
  public static Stream<Arguments> getCustomers() {
    return Stream.of(
        Arguments.arguments(
            new Customer("C. U. Stomer", getMovieRentals().get(0)),
            "Rental Record for C. U. Stomer\n\tYou've Got Mail\t2.0\nAmount owed is 2.0\nYou earned 1 frequent points\n",
            "code = REGULAR and No of days < 2"),
        Arguments.arguments(
            new Customer("C. U. Stomer", getMovieRentals().get(1)),
            "Rental Record for C. U. Stomer\n\tYou've Got Mail\t5.0\nAmount owed is 5.0\nYou earned 1 frequent points\n",
            "code = REGULAR and No of days > 2"),
        Arguments.arguments(
            new Customer("C. U. Stomer", getMovieRentals().get(2)),
            "Rental Record for C. U. Stomer\n\tFast & Furious X\t15.0\nAmount owed is 15.0\nYou earned 2 frequent points\n",
            "code = NEW and No of days = 5"),
        Arguments.arguments(
            new Customer("C. U. Stomer", getMovieRentals().get(3)),
            "Rental Record for C. U. Stomer\n\tCars\t1.5\nAmount owed is 1.5\nYou earned 1 frequent points\n",
            "code = CHILDREN and No of days < 3"),
        Arguments.arguments(
            new Customer("C. U. Stomer", getMovieRentals().get(4)),
            "Rental Record for C. U. Stomer\n\tCars\t6.0\nAmount owed is 6.0\nYou earned 1 frequent points\n",
            "code = CHILDREN and No of days > 3"),
        Arguments.arguments(
            new Customer("C. U. Stomer", getMovieRentals().get(5)),
            "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n",
            "Test with 2 movie rentals"),
        Arguments.arguments(
            new Customer("C. U. Stomer", getMovieRentals().get(6)),
            "Rental Record for C. U. Stomer\n\tYou've Got Mail\t2.0\n\tCars\t1.5\n\tFast & Furious X\t18.0\nAmount owed is 21.5\nYou earned 4 frequent points\n",
            "Test with 3 movie rentals"));
  }
}
