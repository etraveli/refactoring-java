package com.etraveli;

import com.etraveli.model.Customer;
import com.etraveli.repository.CustomerRepository;
import com.etraveli.repository.InitialCustomerRepository;
import com.etraveli.repository.InitialMovieRepository;
import com.etraveli.repository.MovieRepository;
import com.etraveli.service.RentalInfoService;
import com.etraveli.service.RentalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.error("Rental Service was not called with any argument for CustomerId");
            return;
        }

        MovieRepository movieRepository = new InitialMovieRepository();
        CustomerRepository customerRepository = new InitialCustomerRepository();
        RentalService rentalInfoService = new RentalInfoService(movieRepository);

        try {
            for (String arg : args) {
                Customer requestedCustomer = customerRepository.getCustomerById(arg);
                String rentalInfoResult = rentalInfoService.getRentalInfoForCustomer(requestedCustomer);
                logger.info(rentalInfoResult);
            }
        } catch (Exception ex) {
            logger.catching(ex);
        }
    }
}
