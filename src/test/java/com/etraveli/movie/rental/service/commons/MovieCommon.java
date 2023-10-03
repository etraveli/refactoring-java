package com.etraveli.movie.rental.service.commons;

import com.etraveli.movie.rental.service.entities.Movie;

import java.util.List;

public class MovieCommon {
    public static List<Movie> createMovieList() {
        return List.of(
                new Movie(1L, "SuperMan", "Children"),
                new Movie(2L, "Inception", "Regular"),
                new Movie(3L, "The Shawshank Redemption", "New"));
    }

    public static Movie createMovieById(Long Id, String title) {
        return new Movie(Id, title, "Regular");
    }
}
