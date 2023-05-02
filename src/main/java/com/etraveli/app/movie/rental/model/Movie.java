package com.etraveli.app.movie.rental.model;

public class Movie {
    private String title;
    private MovieCategory type;

    public Movie(String title, MovieCategory type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public MovieCategory getType() {
        return type;
    }
}
