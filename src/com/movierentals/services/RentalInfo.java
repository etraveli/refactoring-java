package com.movierentals.services;


import com.movierentals.domain.Customer;
import com.movierentals.domain.Movie;
import com.movierentals.domain.MovieRental;

import static com.movierentals.domain.MovieCategory.*;


public class RentalInfo {

  private MovieRepository movieRepo;

  public RentalInfo(MovieRepository movieRepo) {
    this.movieRepo = movieRepo;
  }

  public String createCustomerReport(Customer customer) {

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + System.lineSeparator());
    for (MovieRental rental : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
        final String movieId = rental.getMovieId();
        final int rentalDays = rental.getDays();
        final Movie movie = movieRepo.findById(movieId);

        if (movie == null) {
            continue;
        }

        switch (movie.getCategory()) {
            case REGULAR:
                thisAmount = 2;
                if (rentalDays > 2) {
                    thisAmount = ((rentalDays - 2) * 1.5) + thisAmount;
                }
                break;
            case NEW:
                thisAmount = rentalDays * 3;
                break;
            case CHILDREN:
                thisAmount = 1.5;
                if (rentalDays > 3) {
                    thisAmount = ((rentalDays - 3) * 1.5) + thisAmount;
                }
                break;
        }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movie.getCategory() == NEW && rentalDays > 2) frequentEnterPoints++;

      //print figures for this rental
      result.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append(System.lineSeparator());
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append(System.lineSeparator());
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points").append(System.lineSeparator());

    return result.toString();
  }
}
