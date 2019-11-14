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

    double totalAmountOwed = 0;
    int totalFrequentBonusPoints = 0;
    StringBuilder report = new StringBuilder("Rental Record for " + customer.getName() + System.lineSeparator());

    for (MovieRental rental : customer.getRentals()) {

      // determine amount for each movie
        final Movie movie = movieRepo.findById(rental.getMovieId());
        final int rentalDays = rental.getDays();

        if (movie == null) {
            continue;
        }

        double amountOwedForMovie = determineAmountOwedForMovie(movie, rentalDays);
        totalAmountOwed += amountOwedForMovie;

        totalFrequentBonusPoints += determineFrequentBonusPointsForMovie(movie, rentalDays);

        //print figures for this rental
      report.append("\t").append(movie.getTitle()).append("\t").append(amountOwedForMovie).append(System.lineSeparator());
    }

    // add footer lines
    report.append("Amount owed is ").append(totalAmountOwed).append(System.lineSeparator());
    report.append("You earned ").append(totalFrequentBonusPoints).append(" frequent points").append(System.lineSeparator());

    return report.toString();
  }

    private double determineAmountOwedForMovie(Movie movie, int rentalDays) {
        double owedAmount = 0;

        switch (movie.getCategory()) {
            case REGULAR:
                owedAmount = 2;
                if (rentalDays > 2) {
                    owedAmount = ((rentalDays - 2) * 1.5) + owedAmount;
                }
                break;
            case NEW:
                owedAmount = rentalDays * 3;
                break;
            case CHILDREN:
                owedAmount = 1.5;
                if (rentalDays > 3) {
                    owedAmount = ((rentalDays - 3) * 1.5) + owedAmount;
                }
                break;
        }

        return owedAmount;
    }

    private int determineFrequentBonusPointsForMovie(Movie movie, int rentalDays) {
        int earnedPoints = 1;

        // add bonus for a two day new release rental
        if (NEW == movie.getCategory() && rentalDays > 2) {
            earnedPoints++;
        }

        return earnedPoints;
    }
}
