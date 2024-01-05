package com.etraveli.controller;

import com.etraveli.modal.request.MovieRentalRequest;
import com.etraveli.modal.response.MovieRentalResponse;
import com.etraveli.service.RentalInfoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api/v1/")
public class MovieRentalController {
    private final Logger logger = getLogger(this.getClass());
    private final RentalInfoService rentalInfoService;

    @Autowired
    public MovieRentalController(RentalInfoService rentalInfoService) {
        this.rentalInfoService = rentalInfoService;
    }

    @GetMapping("/movie-rental/customer/{customerId}")
    public String getMovieRental(@PathVariable Long customerId) {
        logger.info("--ENTER--[GET]--getMovieRental--{}", customerId);

        String result = rentalInfoService.statement(customerId);

        System.out.println("Success");
        logger.info("--EXIT--[GET]--getMovieRental--");
        return result;
    }

    @PostMapping("/movie-rental")
    public Mono<MovieRentalResponse> createMovieRental(@RequestBody @Valid MovieRentalRequest movieRentalRequest) {
        logger.info("--ENTER--[POST]--createMovieRental--Request-- {}", movieRentalRequest);

        MovieRentalResponse movieRentalResponse = this.rentalInfoService.createMovieRental(movieRentalRequest);

        logger.info("--EXIT--[POST]--createMovieRental--");
        return Mono.just(movieRentalResponse);
    }

}
