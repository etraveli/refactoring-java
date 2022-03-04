package services;

import models.Customer;
import models.MovieRental;

import java.util.Arrays;

/**
 * service layer for Customer model
 */
public class CustomerService {
    private static Customer customer = new Customer("C. U. Stomer",Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1)));

    public static Customer getCustomer(){
        return customer;
    }
}
