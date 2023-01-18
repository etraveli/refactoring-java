package com.etraveli.refactoring.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<MovieRental> rentals;
    private double totalCost;
    private int totalPoints;
    
    
    public Customer(String name) {
        this.name = name;
        rentals = new ArrayList<MovieRental>();
    }

    public void addRental(MovieRental rental){
        rentals.add(rental);
        totalCost += rental.getCost();
        totalPoints += rental.getPoints();
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
    
    public double getTotalCost(){
        return totalCost;
    }

    public int getTotalPoints(){
        return totalPoints;
    }
}
