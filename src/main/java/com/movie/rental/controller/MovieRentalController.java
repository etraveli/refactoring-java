package com.movie.rental.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.rental.model.Customer;
import com.movie.rental.service.MovieRentalService;

@RestController
@RequestMapping("/movieRental")
public class MovieRentalController {
    private static final Logger logger = LoggerFactory.getLogger(MovieRentalController.class);

    private final MovieRentalService movieRentalService;

    @Autowired
    public MovieRentalController(MovieRentalService movieRentalService) {
        this.movieRentalService = movieRentalService;
    }

    /**
     * Retrieves rental information for a customer.
     *
     * @param customer The customer for whom rental information is requested.
     * @return ResponseEntity containing the rental information.
     */
    @PostMapping("/rentalInformation")
    public ResponseEntity<String> getRentalInfo(@RequestBody Customer customer) {
        logger.info("Get movie rental information for customer: {}", customer.getName());
        String rentalInfo = movieRentalService.getRentalInfo(customer);
        return ResponseEntity.ok(rentalInfo);
    }
}
