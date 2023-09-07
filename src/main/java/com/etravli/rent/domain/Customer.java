package com.etravli.rent.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ayman
 * Customer object
 */
public class Customer {
    private final String name;

    private List<MovieRental> rentalList;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentalList() {
        return rentalList;
    }


    /**
     * Add Movie rent to the customer
     *
     * @param rental
     */
    public void addRent(MovieRental rental) {

        if (rentalList == null) {
            rentalList = new ArrayList<>();
        }
        rentalList.add(rental);
    }


}
