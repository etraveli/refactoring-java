package services;

import java.util.List;

import data.MovieRental;
import data.Statement;

public abstract class StatementService {

  protected final MoviesService moviesService;

  public StatementService(MoviesService moviesService) {
    this.moviesService = moviesService;
  }

  public abstract Statement createStatement(String customerName, List<MovieRental> movieRentals);

}
