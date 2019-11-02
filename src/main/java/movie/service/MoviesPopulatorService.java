package movie.service;

import movie.dto.Movie;

import java.util.HashMap;

public interface MoviesPopulatorService {

    HashMap<String, Movie> populateMovies();
}
