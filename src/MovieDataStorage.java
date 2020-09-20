import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Movie database access point.
 * We can save and retrieve movie(s) from this end point
**/
public class MovieDataStorage {
	
	private static List<Movie> movies = new ArrayList<Movie>();
	
	public static List<Movie> getAllMovies() {
		return movies;
	}
	
	public static Movie getMovieById(String movieId) {
		List<Movie> filteredById = MovieDataStorage.getAllMovies().stream().filter(m -> m.getMovieId().equals(movieId)).collect(Collectors.toList());
    	return filteredById.size() == 0? null : filteredById.get(0);
	}
	
	public static void addMovie(Movie movie) {
		// Ignore adding if movieId already exists.
		if (movies.stream().filter(m -> m.getMovieId().equals(movie.getMovieId())).findFirst().isPresent()) {
			System.out.println(movie.getTitle() + " is not added due to dupplocate movieId: " + movie.getMovieId());
		}
		
		movies.add(movie);
	}
}
