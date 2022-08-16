package model;

/**
 * @class MovieRental
 * 
 * MISSION: model for renting a movie.
 */
public class MovieRental {
    private String movieId;
    private int days;
    
    /*
	 * CONSTRUCTOR
	 */
    public MovieRental(String movieId, int days) {
        this.movieId = movieId;
        this.days = days;
    }

    /*
	 * GETTERS / SETTERS
	 */
    public String getMovieId() {
        return movieId;
    }

    public int getDays() {
        return days;
    }
}
