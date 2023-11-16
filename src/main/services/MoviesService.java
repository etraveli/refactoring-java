package services;

import records.Movie;

public interface MoviesService {

  public Movie getMovieById(String movieId);

  public double calculateRentPrice(String movieCode, int days);

}
