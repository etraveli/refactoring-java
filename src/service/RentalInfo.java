package service;

import model.Customer;
import model.Movie;
import model.MovieRental;

public class RentalInfo {

  private double totalAmount = 0;
  private int frequentRenterPoints = 0;
  private MovieService movies = MovieService.getInstance();
  private PricingService pricing = PricingService.getInstance();

  private void resetCounters() {
    totalAmount = 0;
    frequentRenterPoints = 0;
  }

  private int getFrequentRenterPointsFor(String category, int days) {
    int points = 1;

    // add bonus for a two day new release rental
    if (category.equals("new") && days > 2) {
      points++;
    }

    return points;
  }

  public String statement(Customer customer) throws IllegalArgumentException {

    resetCounters();

    StringBuilder result = new StringBuilder();
     result.append("Rental Record for ").append(customer.getName()).append("\n");

    for (MovieRental rental : customer.getRentals()) {
      Movie rentedMovie = movies.getMovieById(rental.getMovieId());

      // determine amount for each movie
      double amount = pricing.getPriceForMovie(rental);

      frequentRenterPoints += getFrequentRenterPointsFor(rentedMovie.getCategory(), rental.getDays());

      // print figures for this rental
      result.append(rentedMovie.getTitle()).append("\t").append(amount).append("\n");
      totalAmount = totalAmount + amount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent points\n");

    return result.toString();
  }
}