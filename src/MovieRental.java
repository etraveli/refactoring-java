/**
 * This class holds information about the rented movie id and the number of days that movie is rented.
 * */

public class MovieRental {
    private String rentedMovieId;
    private int movieRentedDays;

    public MovieRental(String movieId, int days) {
        rentedMovieId = movieId;
        movieRentedDays = days;
    }

    public String getRentedMovieId() {
        return rentedMovieId;
    }

    public int getMovieRentedDays() {
        return movieRentedDays;
    }
}
