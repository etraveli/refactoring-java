public class Movie {
	// It is better to add movieId in Movie entity. Then we don't need hash-maps anymore for movieId.
	private String movieId;
	private String title;
	// As movie codes are some predefined constant, we use enum for this attribute.
	private Code code;

	public Movie(String movieId, String title, Code code) {
		this.movieId = movieId;
		this.title = title;
		this.code = code;
	}
	
	public String getMovieId() {
		return movieId;
		}

	public String getTitle() {
		return title;
	}
	
	public Code getCode() {
		return code;
	}
}
