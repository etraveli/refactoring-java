package services;

import java.util.List;

import records.Movie;
import records.MovieRental;

public final class MockStatementService extends StatementService {

  public MockStatementService(MoviesService moviesService) {
    super(moviesService);
  }

  @Override
  public String createStatement(String customerName, List<MovieRental> movieRentals) {
    int frequentEnterPoints = movieRentals.size();
    double totalAmount = 0;
    String rentedMovies = "";
    for (MovieRental movieRental : movieRentals) {

      Movie movie = moviesService.getMovieById(movieRental.movieId());
      String movieCode = movie.code();
      int days = movieRental.days();

      double rentPrice = moviesService.calculateRentPrice(movieCode, days);

      // add bonus for a two day new release rental
      if (movieCode.equals("new") && days > 2)
        frequentEnterPoints++;

      // print figures for this rental
      rentedMovies += "\t" + movie.title() + "\t" + rentPrice + "\n";
      totalAmount = totalAmount + rentPrice;
    }
    return "Rental Record for " + customerName + "\n" +
        rentedMovies +
        "Amount owed is " + totalAmount + "\n" +
        "You earned " + frequentEnterPoints + " frequent points\n";
  }

}
