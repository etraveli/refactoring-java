package com.etraveli.controller;

import com.etraveli.exception.NoCustomerException;
import com.etraveli.exception.NoRentalsException;
import com.etraveli.model.Customer;
import com.etraveli.model.RentalInformation;
import com.etraveli.service.RentalInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/rental-information")
public class RentalInformationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalInformationController.class);

    private final RentalInformationService rentalInformationService;

    public RentalInformationController(RentalInformationService rentalInformationService) {
        this.rentalInformationService = rentalInformationService;
    }

    /**
     * @param customerPayload
     * @return A list of rental information for a given customer, which can be converted into any format for representation.
     * @throws NoCustomerException - when no customer (assuming name to be mandatory) is sent to the API
     * @throws NoRentalsException  - when a customer is present without subscriptions.
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RentalInformation> getRentalInformation(@RequestBody Customer customerPayload) throws NoCustomerException, NoRentalsException {
        LOGGER.info("Get rental information");
        if (!StringUtils.hasLength(customerPayload.getName())) {
            throw new NoCustomerException("Customer is not provided to get Rental Information");
        }
        if (Objects.isNull(customerPayload.getRentals())) {
            throw new NoRentalsException("Customer has No Active Movie Rentals");
        }
        return rentalInformationService.statement(customerPayload);
    }
}
