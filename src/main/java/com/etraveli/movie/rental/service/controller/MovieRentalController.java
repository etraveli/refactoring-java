package com.etraveli.movie.rental.service.controller;

import com.etraveli.movie.rental.service.entities.MovieRental;
import com.etraveli.movie.rental.service.request.RentalDetailsRequest;
import com.etraveli.movie.rental.service.service.MovieRentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rental")
public class MovieRentalController {
    @Autowired
    private final MovieRentalService movieRentalService;

    @PostMapping("/details")
    public ResponseEntity<List<MovieRental>> getRentals(@RequestBody RentalDetailsRequest request) {
        log.info("Retrieve rental details with customerId");
        return new ResponseEntity<>(movieRentalService.getRentalById((long)request.getCustomerId()), HttpStatus.OK);
    }
}
