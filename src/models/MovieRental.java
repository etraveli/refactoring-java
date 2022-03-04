package models;

public class MovieRental {
    private String movieId;
    private Movie movie;
    private int days;

    public MovieRental(String movieId, int days) {
        this.movieId = movieId;
        this.days = days;
    }

    public String getMovieId() {
        return movieId;
    }

    public int getDays() {
        return days;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
