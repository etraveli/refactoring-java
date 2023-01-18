package com.etraveli.refactoring.model;

public class MovieRental {
    private final Movie movie;
    private final int days;
    private final double cost;
    private final int points;

    public MovieRental(Movie movie, int days, double cost, int points) {
        this.movie = movie;
        this.days = days;
        this.cost = cost;
        this.points = points;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDays() {
        return days;
    }

    public double getCost() {
        return cost;
    }

    public double getPoints() {
        return points;
    }

    public static class Builder{

    }
}
