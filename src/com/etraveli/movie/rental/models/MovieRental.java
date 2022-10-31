package com.etraveli.movie.rental.models;

public class MovieRental {
    private final String movieId;
    private final int days;

    public MovieRental(final String movieId, final int days) {
        this.movieId = movieId;
        this.days = days;
    }

    public String getMovieId() {
        return movieId;
    }

    public int getDays() {
        return days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieRental that = (MovieRental) o;

        return movieId.equals(that.movieId);
    }

    @Override
    public int hashCode() {
        return movieId.hashCode();
    }

    @Override
    public String toString() {
        return "MovieRental{" +
                "movieId='" + movieId + '\'' +
                ", days=" + days +
                '}';
    }
}
