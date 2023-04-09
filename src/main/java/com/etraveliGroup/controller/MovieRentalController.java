package com.etraveliGroup.controller;

import com.etraveliGroup.entity.Customer;
import com.etraveliGroup.service.RentalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRentalController {
    @Autowired
    private RentalInfoService rentalInfoService;

    /**
     * Get rental info for the customer
     *
     * @param customer
     * @return String
     */
    @PostMapping("/rentalInfo")
    public String getRentalInfo(@RequestBody Customer customer) {
        return rentalInfoService.getRentalInfo(customer);

    }
}
