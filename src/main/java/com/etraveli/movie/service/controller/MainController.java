package com.etraveli.movie.service.controller;

import com.etraveli.movie.service.dto.RentOrder;
import com.etraveli.movie.service.dto.RentOrderResponse;
import com.etraveli.movie.service.service.MovieRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MainController {

    private final MovieRentalService movieRentalService;

    @PostMapping("/order")
    public ResponseEntity<RentOrderResponse> getOrder(@RequestBody RentOrder order){

       return new ResponseEntity<>(
               movieRentalService.createRentOrderResponse(order),
               HttpStatus.OK
       );
    }
}