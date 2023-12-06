package com.etraveli.assignments.refactoring.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.etraveli.assignments.refactoring.exception.MovieNotFoundException;
import com.etraveli.assignments.refactoring.model.Customer;
import com.etraveli.assignments.refactoring.model.Movie;
import com.etraveli.assignments.refactoring.model.MovieRental;
import com.etraveli.assignments.refactoring.repository.MovieRepository;
import com.etraveli.assignments.refactoring.util.MovieCategory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RentalInfoServiceTest {
  MovieRepository movieRepository = Mockito.mock(MovieRepository.class);

  private final RentalInfoService testRentalInfoService = new RentalInfoService(movieRepository);

  @Test
  void buildRentalInfoStatementSuccess() {

    String expected =
        """
                              Rental Record for C. U. Stomer
                              \tYou've Got Mail\t3.5
                              \tMatrix\t2.0
                              Amount owed is 5.5
                              You earned 2 frequent points
                              """;

    when(movieRepository.getMovie("F001"))
        .thenReturn(new Movie("F001", "You've Got Mail", MovieCategory.REGULAR));
    when(movieRepository.getMovie("F002"))
        .thenReturn(new Movie("F002", "Matrix", MovieCategory.REGULAR));

    List<MovieRental> movieRentals =
        List.of(new MovieRental("F001", 3), new MovieRental("F002", 1));
    var customer = new Customer("C. U. Stomer", movieRentals);
    String actual = testRentalInfoService.buildRentalInfoStatement(customer);
    assertEquals(expected, actual);
  }

  @Test
  void buildRentalInfoStatementMovieNotExist() {

    when(movieRepository.getMovie("F001"))
        .thenReturn(new Movie("F001", "You've Got Mail", MovieCategory.REGULAR));
    when(movieRepository.getMovie("F002")).thenThrow(new MovieNotFoundException("F002"));

    var thrown =
        assertThrows(
            MovieNotFoundException.class,
            () -> {
              List<MovieRental> movieRentals =
                  List.of(new MovieRental("F001", 3), new MovieRental("F002", 1));
              var customer = new Customer("C. U. Stomer", movieRentals);
              String actual = testRentalInfoService.buildRentalInfoStatement(customer);
            });
    assertEquals("Movie not found for Id F002", thrown.getMessage());
  }

  @Test
  void buildRentalInfoStatementWithNewMovie() {

    String expected =
        """
                                  Rental Record for C. U. Stomer
                                  \tYou've Got Mail\t3.5
                                  \tMatrix\t2.0
                                  \tFast & Furious X\t9.0
                                  Amount owed is 14.5
                                  You earned 4 frequent points
                                  """;

    when(movieRepository.getMovie("F001"))
        .thenReturn(new Movie("F001", "You've Got Mail", MovieCategory.REGULAR));
    when(movieRepository.getMovie("F002"))
        .thenReturn(new Movie("F002", "Matrix", MovieCategory.REGULAR));
    when(movieRepository.getMovie("F004"))
        .thenReturn(new Movie("F004", "Fast & Furious X", MovieCategory.NEW));

    List<MovieRental> movieRentals =
        List.of(new MovieRental("F001", 3), new MovieRental("F002", 1), new MovieRental("F004", 3));
    var customer = new Customer("C. U. Stomer", movieRentals);
    String actual = testRentalInfoService.buildRentalInfoStatement(customer);
    assertEquals(expected, actual);
  }
}
