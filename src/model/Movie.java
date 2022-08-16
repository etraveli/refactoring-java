package model;

/**
 * @class Movie
 * 
 * MISSION: model for a movie.
 */
public class Movie {
    private String title;
    private String code;

    /*
	 * CONSTRUCTOR
	 */
    public Movie(String title, String code) {
        this.title = title;
        this.code = code;
    }

    /*
	 * GETTERS / SETTERS
	 */
    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }
}
