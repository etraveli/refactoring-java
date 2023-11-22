package entity;

public class MovieRental {
    private final Movie movie;
    private final int days;

    public MovieRental(Movie movie, int days) {
        this.movie = movie;
        this.days = days;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDays() {
        return days;
    }
}
