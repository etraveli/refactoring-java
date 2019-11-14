package com.movierentals.services;


import com.movierentals.domain.Customer;
import com.movierentals.domain.MovieRental;

import static com.movierentals.domain.MovieCategory.*;


public class RentalInfo {

  private MovieRepository movieRepo;

  public RentalInfo(MovieRepository movieRepo) {
    this.movieRepo = movieRepo;
  }

  public String statement(Customer customer) {

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + System.lineSeparator());
    for (MovieRental rental : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      if (movieRepo.findById(rental.getMovieId()).getCategory() == REGULAR) {
        thisAmount = 2;
        if (rental.getDays() > 2) {
          thisAmount = ((rental.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (movieRepo.findById(rental.getMovieId()).getCategory() == NEW) {
        thisAmount = rental.getDays() * 3;
      }
      if (movieRepo.findById(rental.getMovieId()).getCategory() == CHILDRENS) {
        thisAmount = 1.5;
        if (rental.getDays() > 3) {
          thisAmount = ((rental.getDays() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movieRepo.findById(rental.getMovieId()).getCategory() == NEW && rental.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result.append("\t").append(movieRepo.findById(rental.getMovieId()).getTitle()).append("\t").append(thisAmount).append(System.lineSeparator());
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append(System.lineSeparator());
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points").append(System.lineSeparator());

    return result.toString();
  }
}
