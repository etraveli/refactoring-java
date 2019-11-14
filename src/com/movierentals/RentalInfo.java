package com.movierentals;


import com.movierentals.domain.Customer;
import com.movierentals.domain.Movie;

import java.util.HashMap;
import java.util.Map;

import static com.movierentals.domain.MovieCategory.*;


public class RentalInfo {

  public String statement(Customer customer) {
    Map<String, Movie> movies = new HashMap<>();
    movies.put("F001", new Movie("You've Got Mail", REGULAR));
    movies.put("F002", new Movie("Matrix", REGULAR));
    movies.put("F003", new Movie("Cars", CHILDRENS));
    movies.put("F004", new Movie("Fast & Furious X", NEW));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + System.lineSeparator());
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      if (movies.get(r.getMovieId()).getCategory() == REGULAR) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (movies.get(r.getMovieId()).getCategory() == NEW) {
        thisAmount = r.getDays() * 3;
      }
      if (movies.get(r.getMovieId()).getCategory() == CHILDRENS) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(r.getMovieId()).getCategory() == NEW && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result.append("\t").append(movies.get(r.getMovieId()).getTitle()).append("\t").append(thisAmount).append(System.lineSeparator());
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append(System.lineSeparator());
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points").append(System.lineSeparator());

    return result.toString();
  }
}
