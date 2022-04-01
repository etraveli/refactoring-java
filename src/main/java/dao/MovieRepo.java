package dao;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Singleton;
import model.Movie;
import model.MovieType;

@Singleton
public class MovieRepo {

  private static Map<String, Movie> movies = new HashMap();

  static {
    movies.put("F001", Movie.builder().title("You've Got Mail").type(MovieType.REGULAR).build());
    movies.put("F002", Movie.builder().title("Matrix").type(MovieType.REGULAR).build());
    movies.put("F003", Movie.builder().title("Cars").type(MovieType.CHILDREN).build());
    movies.put("F004", Movie.builder().title("Fast & Furious X").type(MovieType.NEW).build());
  }

  public Movie getMovieById(String id) {
    return movies.get(id);
  }

}
