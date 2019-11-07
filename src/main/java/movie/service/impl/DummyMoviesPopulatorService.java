package movie.service.impl;

import movie.dto.Movie;
import movie.dto.MovieCodes;
import movie.service.MoviesPopulatorService;

import java.util.HashMap;

public class DummyMoviesPopulatorService implements MoviesPopulatorService {

    @Override
    public HashMap<String, Movie> populateMovies() {
        HashMap<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("You've Got Mail", MovieCodes.REGULAR.key));
        movies.put("F002", new Movie("Matrix", MovieCodes.REGULAR.key));
        movies.put("F003", new Movie("Cars", MovieCodes.CHILDRENS.key));
        movies.put("F004", new Movie("Fast & Furious X", MovieCodes.NEW.key));
        return movies;
    }
}
