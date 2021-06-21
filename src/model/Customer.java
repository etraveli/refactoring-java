package model;

import java.util.Set;

public class Customer {

    private String name;
    // assumption: a customer can rent only one copy of a movie
    private Set<MovieRental> rentals;

    public Customer(String name, Set<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public Set<MovieRental> getRentals() {
        return rentals;
    }
}
