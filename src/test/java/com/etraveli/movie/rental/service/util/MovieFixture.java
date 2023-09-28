package com.etraveli.movie.rental.service.util;

import com.etraveli.movie.rental.service.configuration.MovieRentalConfiguration;
import com.etraveli.movie.rental.service.dto.Movie;

import java.util.List;

public class MovieFixture {

    public static List<Movie> createMovieList() {
        return List.of(
                new Movie("F001", "You've Got Mail", MovieRentalConfiguration.MovieCode.REGULAR),
                new Movie("F002", "Matrix", MovieRentalConfiguration.MovieCode.REGULAR),
                new Movie("F003", "Cars", MovieRentalConfiguration.MovieCode.CHILDREN),
                new Movie("F004", "Fast & Furious X", MovieRentalConfiguration.MovieCode.NEW));
    }

    public static Movie createMovieById(String Id, String title) {
        return new Movie(Id, title, MovieRentalConfiguration.MovieCode.REGULAR);
    }
}
