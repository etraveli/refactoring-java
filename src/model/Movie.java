package model;

/**
 * @class Movie
 * 
 * MISSION: model for a movie.
 */
public class Movie {
	
	/**
	 * @class MovieCode
	 * 
	 * MISSION: enum for movie code.
	 */
	public enum MovieCode {
		NEW,
		REGULAR,
		CHILDREN
	}
	
    private String title;
    private MovieCode code;

    /*
	 * CONSTRUCTOR
	 */
    public Movie(String title, MovieCode code) {
        this.title = title;
        this.code = code;
    }

    /*
	 * GETTERS / SETTERS
	 */
    public String getTitle() {
        return title;
    }

    public MovieCode getCode() {
        return code;
    }
}
