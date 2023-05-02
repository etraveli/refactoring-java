package com.etraveli.app.movie.rental.model;

/**
 * Represent information about each movie rental in a statement
 * (b'cz it might need to include more details in future)
 */
public class StatementMovieItem {

    private String movieName;
    private double price;
    private int points;

    public StatementMovieItem(String movieName, double price, int points) {
        this.movieName = movieName;
        this.price = price;
        this.points = points;
    }

    public String getMovieName() {
        return movieName;
    }

    public double getPrice() {
        return price;
    }

    public int getPoints() {
        return points;
    }
}
