import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Movie Class is Changed To add movie, retrieve movie, calculate rental
 */

public class Movie {
	
	private String id;
	private String title;
	private MovieCode code;

	private static List<Movie> movieList = new ArrayList<Movie>();

	public Movie(String id, String title, MovieCode code) {

		this.title = title;
		this.code = code;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public MovieCode getCode() {
		return code;
	}

	public String getId() {
		return id;
	}

	public static void addMovie(Movie movie) {

		movieList.add(movie);
	}

	//Create Movie if not already exist
	public static void addMovie(String id, String title, MovieCode code) {
		if (movieList.stream().noneMatch(movie -> movie.getId().equals(id))) {
			movieList.add(new Movie(id, title, code));
		}
	}

	public static Movie getMovieById(String movieId) {

		List<Movie> output = movieList.stream().filter(movie -> movie.getId().equals(movieId))
				.collect(Collectors.toList());
		if (output == null || output.size() == 0) {
			return null;
		}
		return output.get(0);
	}

	public double getRental(int days) {
		double rental = 0.0;
		
		if (code.equals(MovieCode.REGULAR)) {
			rental = 2;
			if (days > 2) {
				rental = ((days - 2) * 1.5) + rental;
			}
		}

		else if (code.equals(MovieCode.NEW)) {
			rental = days * 3;
		}

		else if (code.equals(MovieCode.CHILDRENS)) {
			rental = 1.5;
			if (days > 3) {
				rental = ((days - 3) * 1.5) + rental;
			}
		} else {

		}

		return rental;
	}

	public boolean isBonusEligible(int days) {
		if (code == MovieCode.NEW && days > 2) {
			return true;
		}
		return false;
	}
}
