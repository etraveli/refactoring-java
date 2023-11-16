package services;

import java.util.List;

import records.MovieRental;

public abstract class StatementService {

  protected final MoviesService moviesService;

  public StatementService(MoviesService moviesService) {
    this.moviesService = moviesService;
  }

  public abstract String createStatement(String customerName, List<MovieRental> movieRentals);

}
