package com.etraveli.movie.rental.service.controller;

import com.etraveli.movie.rental.service.entities.Movie;
import com.etraveli.movie.rental.service.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public Movie getMovieById(@PathVariable Long movieId) {
        return movieService.getMovieByMovieId(movieId);
    }
}
