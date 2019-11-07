package movie.persistence;

import movie.dto.Movie;
import movie.service.MoviesPopulatorService;
import movie.service.impl.DummyMoviesPopulatorService;

import java.util.HashMap;
import java.util.Optional;

public class MovieRentalRepository {

    private static MoviesPopulatorService moviesPopulatorService = new DummyMoviesPopulatorService();

    private static HashMap<String, Movie> movies = moviesPopulatorService.populateMovies();

    public Optional<Movie> getMovieById(String movieId) {
        return movies.containsKey(movieId) ? Optional.of(movies.get(movieId)) : Optional.empty();
    }
}
