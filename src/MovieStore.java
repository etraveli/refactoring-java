import java.util.HashMap;

public class MovieStore {
	private static MovieStore store = new MovieStore();
	
	private HashMap<String, Movie> movies = new HashMap<>();
	
	public static MovieStore getInstance() {
		return store;
	}
	
	public Movie getMovie(String movieId) {
		return movies.get(movieId);
	}
	
	private MovieStore() {
		addMovie(new Movie("F001", "You've Got Mail", Movie.Category.REGULAR));
		addMovie(new Movie("F002", "Matrix", Movie.Category.REGULAR));
		addMovie(new Movie("F003", "Cars", Movie.Category.CHILDRENS));
		addMovie(new Movie("F004", "Fast & Furious X", Movie.Category.NEW));
	}
	
	private void addMovie(Movie movie) {
		movies.put(movie.getMovieId(), movie);
	}
}
