package com.movie.rental.app.controller;

import com.movie.rental.app.model.Customer;
import com.movie.rental.app.service.RentalInfoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This RentalInfoController class is used to handle requests related to rental information
 *
 * @author pabasara8857@gmail.com
 */
@RestController
@RequestMapping("/rental-info")
public class RentalInfoController {

  @Autowired private RentalInfoService rentalInfoService;

  /**
   * Handle request of generating rental information statement
   *
   * @param customer Customer request object
   * @return ResponseEntity with generated rental information statement
   */
  @Operation(summary = "Generate rental information statement")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> generateRentalInfo(@Valid @RequestBody Customer customer) {
    return new ResponseEntity(
        rentalInfoService.generateRentalInfoStatement(customer), HttpStatus.OK);
  }
}
