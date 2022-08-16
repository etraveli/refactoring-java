package model;

import java.util.List;

/**
 * @class Customer
 * 
 * MISSION: model for a customer.
 */
public class Customer {
    private String name;
    private List<MovieRental> rentals;
    
    /*
	 * CONSTRUCTOR
	 */
    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    /*
	 * GETTERS / SETTERS
	 */
    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
}
