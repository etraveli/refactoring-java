package com.etraveli.assignments.refactoring;

import com.etraveli.assignments.refactoring.models.Customer;
import com.etraveli.assignments.refactoring.models.Movie;
import com.etraveli.assignments.refactoring.models.MovieRental;

import java.util.HashMap;

public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.name() + "\n";
    for (MovieRental r : customer.rentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      if (movies.get(r.movieId()).code().equals("regular")) {
        thisAmount = 2;
        if (r.days() > 2) {
          thisAmount = ((r.days() - 2) * 1.5) + thisAmount;
        }
      }
      if (movies.get(r.movieId()).code().equals("new")) {
        thisAmount = r.days() * 3;
      }
      if (movies.get(r.movieId()).code().equals("childrens")) {
        thisAmount = 1.5;
        if (r.days() > 3) {
          thisAmount = ((r.days() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(r.movieId()).code() == "new" && r.days() > 2) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movies.get(r.movieId()).title() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
