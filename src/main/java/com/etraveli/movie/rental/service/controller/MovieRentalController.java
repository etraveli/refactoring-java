package com.etraveli.movie.rental.service.controller;

import com.etraveli.movie.rental.service.dto.MovieRentalRequest;
import com.etraveli.movie.rental.service.dto.MovieRentalResponse;
import com.etraveli.movie.rental.service.service.MovieRentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieRentalController {

    private final MovieRentalService movieRentalService;

    @PostMapping(path = "/rental")
    public ResponseEntity<MovieRentalResponse> getMovieRental(@Valid @RequestBody MovieRentalRequest request) {
        log.info("rental request received with id : {}", request.getCustomerId());
        return new ResponseEntity<>(movieRentalService.getMovieRentalResponse(request), HttpStatus.OK);
    }
}
