package entities;

import java.util.Collections;
import java.util.List;

public class Customer {

	private String name;
	private List<MovieRental> rentals;

	public Customer(String name, List<MovieRental> rentals) {
		this.name = name;
		this.rentals = rentals;
	}

	public String getName() {
		return name;
	}

	// COMMENT Avoid null pointer exception
	public List<MovieRental> getRentals() {
		return rentals != null ? rentals : Collections.emptyList();
	}
}
