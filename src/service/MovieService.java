package service;

import java.util.HashMap;

import model.Movie;

public class MovieService {

  private static MovieService instance = null;
  private HashMap<String, Movie> movies = new HashMap<>();

  private MovieService() {
    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));
    movies.put("T001", new Movie("Unknown Pricing", "N/N"));
  }

  public static MovieService getInstance() {
    if (instance == null) {
    instance = new MovieService();
    }
    return instance;
  }

  public Movie getMovieById(String movieId) {
    return movies.get(movieId);
  }
}