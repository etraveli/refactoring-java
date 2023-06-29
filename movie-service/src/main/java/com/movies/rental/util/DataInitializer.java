package com.movies.rental.util;

import com.movies.rental.service.MovieService;
import com.movies.rental.service.repository.entity.Movie;
import com.movies.rental.service.repository.entity.MovieType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;

@Component
public class DataInitializer implements ApplicationRunner {
    private final MovieService movieService;

    public DataInitializer(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Movie movie1 = new Movie("F001", "You've Got Mail", MovieType.REGULAR);
        Movie movie2 = new Movie("F002", "Matrix", MovieType.REGULAR);
        Movie movie3 = new Movie("F003", "Cars", MovieType.CHILDRENS);
        Movie movie4 = new Movie("F004", "Fast & Furious X", MovieType.NEW);
        Movie movie5 = new Movie("F005", "Spiderman", MovieType.NEW);

        movieService.save(movie1);
        movieService.save(movie2);
        movieService.save(movie3);
        movieService.save(movie4);
        movieService.save(movie5);

    }
}
