package com.etraveli.refactoring.service;

import com.etraveli.refactoring.model.*;
import com.etraveli.refactoring.repository.*;
import com.etraveli.refactoring.util.RentalRules;

public class RentalInfo {
  
    private final MovieDB movies;
    private final CustomerDB customers;

    public RentalInfo(MovieDB movies, CustomerDB customers){
        this.movies =movies;
        this.customers = customers;
    }

    public void createCustomer(String id, String name){
        customers.create(id, name);
    }

    public void addRental(String customerID, String movieID, int days){
        Customer customer = customers.get(customerID);
        Movie movie =  movies.get(movieID);

        MovieRental rental = new MovieRental(movie, 
                                            days, 
                                            RentalRules.cost(movie.getCode(), days), 
                                            RentalRules.points(movie.getCode(), days));
            
        customer.addRental(rental);
    }

    public String statement(String customerID) {
        Customer customer = customers.get(customerID);
        
        Statement statement = Statement
        .name(customer.getName())
        .rentals(customer.getRentals())
        .totalCost(customer.getTotalCost())
        .totalPoints(customer.getTotalPoints())
        .build();

        return statement.toString();
    }
}
