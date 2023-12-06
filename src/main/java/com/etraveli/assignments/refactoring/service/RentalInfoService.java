package com.etraveli.assignments.refactoring.service;

import com.etraveli.assignments.refactoring.model.Customer;
import com.etraveli.assignments.refactoring.model.Movie;
import com.etraveli.assignments.refactoring.model.MovieRental;
import com.etraveli.assignments.refactoring.repository.MovieRepository;
import com.etraveli.assignments.refactoring.util.Constants;
import com.etraveli.assignments.refactoring.util.MovieCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** The Business logic of Rental info service are kept in this class. */
public class RentalInfoService {
  private static final Logger LOGGER = LogManager.getLogger(RentalInfoService.class);
  private final MovieRepository movieRepository;

  /**
   * Instantiates a new Rental info service.
   *
   * @param movieRepository the movie repository
   */
  public RentalInfoService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  /**
   * Build rental info statement string according to the business requirement.
   *
   * @param customer the customer
   * @return the constructed retail information string for given customer.
   */
  public String buildRentalInfoStatement(Customer customer) {
    double totalAmount = 0;
    int frequentEnterPoints = 0;

    StringBuilder result = new StringBuilder("Rental Record for " + customer.name() + "\n");

    for (MovieRental rental : customer.rentals()) {
      // determine amount for each movie
      Movie currentMovie = movieRepository.getMovie(rental.movieId());
      double thisAmount = calculateAmountForMovie(rental.days(), currentMovie);

      // add frequent bonus points
      frequentEnterPoints =
          addFrequentBonusPoints(rental.days(), frequentEnterPoints, currentMovie);

      // print figures for this rental
      result.append("\t").append(currentMovie.title()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }

  /**
   * Calculates the bonus points for renting movies.
   *
   * @param rentalDays
   * @param frequentEnterPoints
   * @param currentMovie
   * @return calculated bonus points.
   */
  private int addFrequentBonusPoints(int rentalDays, int frequentEnterPoints, Movie currentMovie) {
    frequentEnterPoints++;
    // add bonus for a two-day new release rental
    if (currentMovie.movieCategory() == MovieCategory.NEW
        && rentalDays > Constants.BONUS_THRESHOLD_DAYS) {
      frequentEnterPoints++;
    }
    LOGGER.debug(
        "Calculated frequent bonus points for {} : {}",
        currentMovie.movieId(),
        frequentEnterPoints);
    return frequentEnterPoints;
  }

  /**
   * Calculates the rental amount for the movie.
   *
   * @param days
   * @param currentMovie
   * @return
   */
  private double calculateAmountForMovie(int days, Movie currentMovie) {
    double thisAmount = 0.0;
    MovieCategory thisCategory = currentMovie.movieCategory();
    switch (thisCategory) {
      case REGULAR, CHILDRENS -> {
        thisAmount = thisCategory.getBaseAmount();
        if (days > thisCategory.getDefaultAllowedDays()) {
          thisAmount =
              ((days - thisCategory.getDefaultAllowedDays())
                      * thisCategory.getExtraDaysMultiplier())
                  + thisAmount;
        }
      }
      case NEW -> thisAmount = days * thisCategory.getExtraDaysMultiplier();
    }
    LOGGER.debug("Calculated amount for movie {} , is {}", currentMovie.movieId(), thisAmount);
    return thisAmount;
  }
}
