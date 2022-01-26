package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

  private String name;
  private List<MovieRental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public Customer(String name, List<MovieRental> rentals) {
    this(name);

    if (rentals != null) {
      this.rentals = rentals;
    }
  }

  public String getName() {
    return name;
  }

  public List<MovieRental> getRentals() {
    return rentals;
  }

  public void rent(String movieId, int days) {
    rentals.add(new MovieRental(movieId, days));
  }
}