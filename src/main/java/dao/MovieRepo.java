package dao;

import java.util.HashMap;
import javax.inject.Singleton;
import model.Movie;
import model.MovieType;

@Singleton
public class MovieRepo {

  private static HashMap<String, Movie> movies = new HashMap();

  static {
    movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR));
    movies.put("F002", new Movie("Matrix", MovieType.REGULAR));
    movies.put("F003", new Movie("Cars", MovieType.CHILDREN));
    movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW));
  }

  public Movie getMovieById(String id) {
    return movies.get(id);
  }

}
