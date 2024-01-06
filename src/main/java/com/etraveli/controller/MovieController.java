package com.etraveli.controller;

import com.etraveli.modal.request.MovieRequest;
import com.etraveli.modal.response.MovieResponse;
import com.etraveli.service.MovieService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api/v1/")
public class MovieController {
    private final Logger logger = getLogger(this.getClass());
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movie")
    public Mono<MovieResponse> createMovie(@RequestBody @Valid MovieRequest MovieRequest) {
        logger.info("--ENTER--[POST]--createMovie--Request-- {}", MovieRequest);

        MovieResponse movieResponse = this.movieService.createMovie(MovieRequest);

        logger.info("--EXIT--[POST]--createMovie--");
        return Mono.just(movieResponse);
    }

    @PutMapping("/movie")
    public Mono<MovieResponse> updateMovie(@RequestBody MovieRequest movieRequest) {
        logger.info("--ENTER--[PUT]--updateMovie--Request-- {}", movieRequest);

        MovieResponse movieResponse = this.movieService.updateMovie(movieRequest);

        logger.info("--EXIT--[PUT]--updateMovie--");
        return Mono.just(movieResponse);
    }

    @GetMapping("/movie/{movieCode}")
    public Mono<MovieResponse> getMovieByMovieCode(@PathVariable String movieCode) {
        logger.info("--ENTER--[GET]--getMovieByMovieCode--{}", movieCode);

        MovieResponse movieResponse = this.movieService.getMovieByMovieCode(movieCode);

        logger.info("--EXIT--[GET]--getCustomerById--");
        return Mono.just(movieResponse);
    }

    @GetMapping("/movies")
    public Flux<MovieResponse> getAllMovies() {
        logger.info("--ENTER--[GET]--getAllMovies--");

        List<MovieResponse> movieResponses = this.movieService.getAllMovies();

        logger.info("--EXIT--[GET]--getAllMovies--");
        return Flux.fromIterable(movieResponses);
    }
}
