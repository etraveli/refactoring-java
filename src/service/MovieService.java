package service;

import java.util.HashMap;
import java.util.Map;

import model.Movie;
import model.Movie.MovieCode;

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
		movies.put("F001", new Movie("You've Got Mail", MovieCode.REGULAR));
		movies.put("F002", new Movie("Matrix", MovieCode.REGULAR));
		movies.put("F003", new Movie("Cars", MovieCode.CHILDREN));
		movies.put("F004", new Movie("Fast & Furious X", MovieCode.NEW));
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
