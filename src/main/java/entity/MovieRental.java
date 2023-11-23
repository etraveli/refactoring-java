package entity;

import org.jetbrains.annotations.NotNull;

public class MovieRental {
    private final Movie movie;
    private final int days;

    public MovieRental(@NotNull Movie movie, int days) {
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
