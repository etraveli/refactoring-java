package com.etraveli.controller;

import com.etraveli.exceptiondomain.DataNotFoundException;
import com.etraveli.exceptiondomain.constant.ExceptionConstant;
import com.etraveli.modal.request.CustomerRequest;
import com.etraveli.service.impl.CustomerServiceImpl;
//import com.etraveli.service.RentalInfoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api/v1/movie-rental")
public class MovieRentalController {
    private final Logger logger = getLogger(this.getClass());

    private final CustomerServiceImpl customerService;

    @Autowired
    public MovieRentalController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/{customerId}")
    public String getMovieRental(@PathVariable Long customerId) {
        logger.info("--ENTER--[GET]--getMovieRental--{}", customerId);

        CustomerRequest customer = this.customerService.getCustomerByCustomerId(customerId);

        if (customer == null) {
            logger.info("--ERROR--[GET]--DataNotFoundException--{}", ExceptionConstant.CUSTOMER_NOT_FOUND);
            throw new DataNotFoundException(ExceptionConstant.CUSTOMER_NOT_FOUND);
        }

/*
        RentalInfoService rentalInfoService = new RentalInfoService();
        String result = rentalInfoService.statement(customer);
*/
        String result = null;
        System.out.println("Success");
        logger.info("--EXIT--[GET]--getMovieRental--");
        return result;
    }
}
