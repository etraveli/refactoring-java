package com.movierentals;


public class Movie {
    private String title;
    private MovieCategory category;

    public Movie(String title, MovieCategory category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public MovieCategory getCategory() {
        return category;
    }
}
