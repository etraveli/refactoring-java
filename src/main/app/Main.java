package app;

import java.util.Arrays;
import java.util.List;

import data.MovieRental;
import data.Statement;

import services.MockMoviesService;
import services.MockStatementService;
import services.MoviesService;
import services.StatementService;

public final class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
    List<MovieRental> movieRentals = Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1));
    MoviesService moviesService = new MockMoviesService();
    StatementService statementService = new MockStatementService(moviesService);
    Statement statement = statementService.createStatement("C. U. Stomer", movieRentals);
    if (!expected.equals(statement.toString())) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator()
          + System.lineSeparator() + "Got: " + System.lineSeparator() + statement);
    }
    System.out.print(statement);
  }

}
