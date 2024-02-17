package com.example.rentalapi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rentalapi.constants.APIConstant;
import com.example.rentalapi.model.Customer;
import com.example.rentalapi.service.RentalService;

@RestController
@RequestMapping(APIConstant.RENTAL_API_BASE_PATH)
public class RentalController {

	@Autowired
    private RentalService rentalService;

    @PostMapping(APIConstant.GENERATE_STATEMENT_PATH)
    public ResponseEntity<String> generateRentalStatement(@RequestBody Customer customer) { 
    	String rentalStatement = rentalService.generateRentalStatement(customer);
    	return ResponseEntity.ok(rentalStatement);
    }
}
