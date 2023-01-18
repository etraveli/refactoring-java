package com.etraveli.refactoring.model;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    
    private final String name;
    private final List<String> rentals;
    private final String totalCost;
    private final String totalPoints;

    private Statement(Builder builder){
        this.name = builder.name;
        this.rentals = new ArrayList<String>();
        for (String rental : builder.rentals) {
            this.rentals.add(rental);
        }
        this.totalCost = builder.totalCost;
        this.totalPoints = builder.totalPoints;
    }

    @Override
	public String toString() {
		StringBuilder statement = new StringBuilder();
        
        statement.append(name);
        for (String rental : rentals) {
            //print figures for this rental
            statement.append(rental);
        }
        // add footer lines
        statement.append(totalCost);
        statement.append(totalPoints);

        return statement.toString();
	}

    public static Builder name(String name){
        return new Builder(name);
    } 
    
    public static class Builder{
        private final String name;
        private List<String> rentals;
        private String totalCost;
        private String totalPoints;

        private Builder(String name){
            this.name = "Rental Record for " + name + System.lineSeparator();

        }
    
        public Builder rentals(List<MovieRental> rentals){
            this.rentals = new ArrayList<String>();
            for (MovieRental rental : rentals) {
                this.rentals.add("\t" + rental.getMovie().getTitle() + "\t" + rental.getCost() + System.lineSeparator());
            }
            return this;
        }
    
        public Builder totalCost(double cost){
            this.totalCost = "Amount owed is " + cost + System.lineSeparator();
            return this;
        }
    
        public Builder totalPoints(int points){
            this.totalPoints = "You earned " + points + " frequent points" + System.lineSeparator();
            return this;
        }
    
        public Statement build() {
            return new Statement(this);
        }
    }
}
