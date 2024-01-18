package com.etraveli.repository;

import com.etraveli.model.Movie;
import com.etraveli.model.MovieCode;
import com.etraveli.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class InitialMovieRepository implements MovieRepository {
    private final HashMap<String, Movie> movies = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(InitialMovieRepository.class);

    public InitialMovieRepository() {
        movies.put("F001", new Movie("You've Got Mail", MovieCode.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieCode.REGULAR));
        movies.put("F003", new Movie("Cars", MovieCode.CHILDREN));
        movies.put("F004", new Movie("Fast & Furious X", MovieCode.NEW));
    }

    /**
     * Returns Movie from "HardCoded" HashMap for requested movieId. Logs error if movie not found.
     *
     * @param movieId textual identifier for which the Movie should be returned
     * @return Movie from hardcoded hashmap for requested movieId
     */
    @Override
    public Movie getMovieById(String movieId) {
        Movie movie = movies.get(movieId);
        if (movie == null) {
            logger.error(Constants.MOVIE_REPO_MISSING_CUSTOMER);
        }

        return movie;
    }
}
