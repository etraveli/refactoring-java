package com.etraveli.movie.rental.service.controller;

import com.etraveli.movie.rental.service.dto.MovieRentalRequest;
import com.etraveli.movie.rental.service.dto.MovieRentalResponse;
import com.etraveli.movie.rental.service.service.MovieRentalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieRentalController {

    private final MovieRentalService movieRentalService;

    @PostMapping(path = "/rental")
    public ResponseEntity<MovieRentalResponse> getMovieRental(@Valid @RequestBody MovieRentalRequest request) {
        return new ResponseEntity<>(movieRentalService.getMovieRentalResponse(request), HttpStatus.OK);
    }
}
