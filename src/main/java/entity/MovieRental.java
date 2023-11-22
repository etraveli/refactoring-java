package entity;

public class MovieRental {
    private final Movie.Id movieId;
    private final int days;

    public MovieRental(Movie.Id movieId, int days) {
        this.movieId = movieId;
        this.days = days;
    }

    public Movie.Id getMovieId() {
        return movieId;
    }

    public int getDays() {
        return days;
    }
}
