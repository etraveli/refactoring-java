package com.etraveli.movie.rental.service.controller;

import com.etraveli.movie.rental.service.request.RentalDetailsRequest;
import com.etraveli.movie.rental.service.response.RentalDetailsResponse;
import com.etraveli.movie.rental.service.service.MovieRentalService;
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
@RequestMapping("/rental-service/rental")
public class MovieRentalController {
    private final MovieRentalService movieRentalService;

    @PostMapping("/details")
    public ResponseEntity<RentalDetailsResponse> getCustomerRental(@RequestBody RentalDetailsRequest request) {
        log.info("Retrieve rental details with customerId");
        return new ResponseEntity<>(movieRentalService.getCustomerRental(request), HttpStatus.OK);
    }
}
