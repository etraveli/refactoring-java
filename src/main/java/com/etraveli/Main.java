package com.etraveli;

import com.etraveli.model.Customer;
import com.etraveli.repository.InitialCustomerRepository;
import com.etraveli.repository.InitialMovieRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.error("Rental Service was not called with any argument for CustomerId");
            return;
        }

        try {
            for (String arg : args) {
                InitialMovieRepository movieRepository = new InitialMovieRepository();
                InitialCustomerRepository customerRepository = new InitialCustomerRepository();
                RentalInfo rentalInfo = new RentalInfo(movieRepository);

                Customer requestedCustomer = customerRepository.getCustomerById(arg);

                String rentalInfoResult = rentalInfo.getRentalInfoForCustomer(requestedCustomer);

                logger.info(rentalInfoResult);
            }
        } catch (Exception ex) {
            logger.catching(ex);
        }
    }
}
