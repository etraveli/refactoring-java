package com.etraveli.repository;

import com.etraveli.model.Customer;
import com.etraveli.model.MovieRental;
import com.etraveli.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;

public class InitialCustomerRepository implements CustomerRepository {
    private final HashMap<String, Customer> customers = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(InitialCustomerRepository.class);

    public InitialCustomerRepository() {
        customers.put("C001", new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));
        customers.put("C002", new Customer("Children Customer", Arrays.asList(new MovieRental("F003", 7), new MovieRental("F003", 1))));
        customers.put("C003", new Customer("News Customer", Arrays.asList(new MovieRental("F004", 1), new MovieRental("F004", 3))));
        customers.put("C004", new Customer("Mix Customer", Arrays.asList(new MovieRental("F001", 1), new MovieRental("F002", 2),
                new MovieRental("F003", 3), new MovieRental("F004", 4))));
        customers.put("C005", new Customer("Skip Customer", Arrays.asList(new MovieRental("F001", -1), new MovieRental("F002", 4))));
    }

    /**
     * Returns Customer from "HardCoded" HashMap for requested customerId. Logs error if customer not found.
     *
     * @param customerId textual identifier for which the Customer should be returned
     * @return Customer from hardcoded hashmap for requested customerId
     */
    @Override
    public Customer getCustomerById(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            logger.error(Constants.CUSTOMER_REPO_MISSING_CUSTOMER);
        }

        return customer;
    }
}
