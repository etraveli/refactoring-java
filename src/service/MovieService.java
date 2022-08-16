package service;

import java.util.HashMap;
import java.util.Map;

import model.Movie;

/**
 * @class MovieService
 * 
 * MISSION: service which provides the movies.
 */
public class MovieService {
	
	private Map<String, Movie> movies = new HashMap<>();

	/*
	 * INITIALIZING BLOCK
	 */
	{
		movies.put("F001", new Movie("You've Got Mail", "regular"));
		movies.put("F002", new Movie("Matrix", "regular"));
		movies.put("F003", new Movie("Cars", "childrens"));
		movies.put("F004", new Movie("Fast & Furious X", "new"));
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public MovieService() {}
	
	/*
	 * GETTERS / SETTERS
	 */
	public Map<String, Movie> getMovies() {
		return movies;
	}

}
