package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import entities.Movie;
import entities.MovieCode;

public class DatabaseClass {

	/*
	 * COMMENT Creating this class to act as DB instance, Not right way to do it-reason- No thread safety
	 * Implementing only for the demo purpose
	 *
	 */

	private static final HashMap<String, Movie> movies = loadMoviesMap();

	public static HashMap<String, Movie> loadMoviesMap() {
		HashMap<String, Movie> data = new HashMap<>();
		data.put("F001", new Movie("You've Got Mail", MovieCode.REGULAR));
		data.put("F002", new Movie("Matrix", MovieCode.REGULAR));
		data.put("F003", new Movie("Cars", MovieCode.CHILDREN));
		data.put("F004", new Movie("Fast & Furious X", MovieCode.NEW));
		// COMMENT Wrong data for test
		data.put("F005", new Movie("Inception", null));
		return data;
	}

	public List<Movie> getAllMovies() {
		return new ArrayList<>(movies.values());
	}

	public Optional<Movie> getMovieById(String id) {
		return Optional.ofNullable(movies.getOrDefault(id, null));
	}

}
