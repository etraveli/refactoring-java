package com.etraveli;

import com.etraveli.model.Customer;
import com.etraveli.model.Movie;
import com.etraveli.model.MovieRental;
import com.etraveli.repository.MovieRepository;

import java.util.HashMap;

public class RentalInfo {
  private MovieRepository movieRepository;

  public RentalInfo(MovieRepository movieRepository){
    this.movieRepository = movieRepository;
  }

  public String statement(Customer customer) {
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.name() + "\n";
    for (MovieRental rental : customer.rentals()) {
      Movie movie = movieRepository.getMovieById(rental.movieId());
      double thisAmount  = movie.code().amount(rental.days());
      frequentEnterPoints += movie.code().frequentEnterPoints(rental.days());

      //print figures for this rental
      result += "\t" + movie.title() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
