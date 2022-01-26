package service;

import java.util.HashMap;

import model.Movie;

public class MovieService {

  private static MovieService instance = null;
  private HashMap<String, Movie> moviesCurrentlyPlaying = new HashMap<>();

  private MovieService() {
    moviesCurrentlyPlaying.put("F001", new Movie("You've Got Mail", "regular"));
    moviesCurrentlyPlaying.put("F002", new Movie("Matrix", "regular"));
    moviesCurrentlyPlaying.put("F003", new Movie("Cars", "childrens"));
    moviesCurrentlyPlaying.put("F004", new Movie("Fast & Furious X", "new"));
    moviesCurrentlyPlaying.put("T001", new Movie("Unknown Pricing", "N/N"));
  }

  public static MovieService getInstance() {
    if (instance == null) {
    instance = new MovieService();
    }
    return instance;
  }

  public Movie getMovieById(String movieId) {
    return moviesCurrentlyPlaying.get(movieId);
  }
}