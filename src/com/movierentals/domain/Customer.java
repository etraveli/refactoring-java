package com.movierentals.domain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer {
    private String name;
    private List<MovieRental> rentals = new ArrayList<>();

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public Customer(String name, MovieRental... rentals) {
        this.name = name;
        this.rentals.addAll(Arrays.asList(rentals));
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
}
