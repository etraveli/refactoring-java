package com.example.rentalapi.model;
import java.util.ArrayList;
import java.util.List;
public class Customer {
    public Customer(String name, List<MovieRental> rentals) {
		super();
		this.name = name;
		this.rentals = rentals;
	}

	private String name;
    private List<MovieRental> rentals;
    
    public Customer(String name) {
        this.name = name;
        this.rentals = new ArrayList<>();
    }
	public void setRentals(List<MovieRental> rentals) {
		this.rentals = rentals;
	}

	public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
}
