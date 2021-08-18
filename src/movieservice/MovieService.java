package movieservice;

import java.util.HashMap;

import domain.Movie;

public class MovieService {
	private HashMap<String, Movie> movies = new HashMap<>();
	private static MovieService instance = null;
	
	public static MovieService getInstance() {
		if(instance != null) {
			return instance;
		}
		instance = new MovieService();
		return instance;
	}
	
	private MovieService() {
		movies.put("F001", new Movie("You've Got Mail", "regular"));
		movies.put("F002", new Movie("Matrix", "regular"));
		movies.put("F003", new Movie("Cars", "childrens"));
		movies.put("F004", new Movie("Fast & Furious X", "new"));
	}
	
	public Movie get(String movieId) {
		return movies.get(movieId);
	}
}
