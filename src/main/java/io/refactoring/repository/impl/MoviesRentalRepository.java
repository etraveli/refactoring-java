package io.refactoring.repository.impl;

import io.refactoring.model.Movie;
import io.refactoring.model.MovieCode;
import io.refactoring.repository.IMoviesRentalRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoviesRentalRepository implements IMoviesRentalRepository {

    private static Map<String, Movie> movies = new HashMap<String, Movie>();
    private final String path;

    public MoviesRentalRepository(String path){
        this.path = path;
    }

    private void moviesFromFile() throws IOException {
        List<String> movieLines = Files.readAllLines(Paths.get(path));
        movieLines.forEach(
                line -> {
                    var movieDetails = line.split(";");
                    movies.put(
                            movieDetails[0],
                            new Movie(movieDetails[1], MovieCode.valueOfGenre(movieDetails[2]))
                    );
                }
        );
    }

    @Override
    public Movie movie(String movieId) {
        if (movies.isEmpty()) {
            try {
                moviesFromFile();
            } catch (IOException e) {
                throw new RuntimeException("Error reading from file");
            }
        }
        Movie result = movies.get(movieId);
        if(result==null) throw new RuntimeException("Movie with movieId " + movieId + " not found.");
        return movies.get(movieId);
    }

}
