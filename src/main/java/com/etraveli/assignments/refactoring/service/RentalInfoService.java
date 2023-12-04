package com.etraveli.assignments.refactoring.service;

import com.etraveli.assignments.refactoring.model.Customer;
import com.etraveli.assignments.refactoring.model.Movie;
import com.etraveli.assignments.refactoring.model.MovieRental;
import com.etraveli.assignments.refactoring.repository.MovieRepository;
import com.etraveli.assignments.refactoring.util.Constants;
import com.etraveli.assignments.refactoring.util.MovieCategory;

import java.util.Map;

public class RentalInfoService {
  private final MovieRepository movieRepository;

  public RentalInfoService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }
  public String statement(Customer customer) {
    Map<String, Movie> movies = movieRepository.getPreConfiguredMovies();

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    
    StringBuilder result = new StringBuilder("Rental Record for " + customer.name() + "\n");
    for (MovieRental rental : customer.rentals()) {
      // determine amount for each movie
      Movie currentMovie = movies.get(rental.movieId());
      double thisAmount  = calculateAmountForMovie(rental, currentMovie);

      //add frequent bonus points
      frequentEnterPoints = addFrequentBonusPoints(rental.days(), frequentEnterPoints, currentMovie);

      //print figures for this rental
      result.append("\t").append(currentMovie.title()).append("\t").append(thisAmount).append("\n");
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }

  private int addFrequentBonusPoints(int rentalDays, int frequentEnterPoints, Movie currentMovie) {
    frequentEnterPoints++;
    // add bonus for a two day new release rental
    if (currentMovie.movieCategory() == MovieCategory.NEW && rentalDays > Constants.BONUS_THRESHOLD_DAYS) {
        frequentEnterPoints++;
    }
    return frequentEnterPoints;
  }

  private double calculateAmountForMovie(MovieRental r, Movie currentMovie) {
    double thisAmount = 0.0;
    MovieCategory thisCategory = currentMovie.movieCategory();
    switch (thisCategory) {
      case REGULAR , CHILDRENS -> {
        thisAmount = thisCategory.getBaseAmount();
        if (r.days() > thisCategory.getDefaultAllowedDays()) {
          thisAmount = ((r.days() - thisCategory.getDefaultAllowedDays()) * thisCategory.getExtraDaysMultiplier()) + thisAmount;
        }
      }
      case NEW -> thisAmount = r.days() * thisCategory.getExtraDaysMultiplier();
    }
    return thisAmount;
  }
}
