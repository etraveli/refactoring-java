package main.java.movie;

import java.util.Map;

public interface IMovieRepository {
    Map<String, Movie> getMovies();
}
