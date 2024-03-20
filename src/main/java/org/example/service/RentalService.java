package org.example.service;

import org.example.entity.*;
import org.example.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

  @Autowired
  private RentalInfoRepository rentalInfoRepository;

  public String statement(RentalInfo rentalInfo) {
    StringBuilder result = new StringBuilder("Rental Record for " + rentalInfo.getCustomer().getName() + "\n");

    for (RentalDetail rentalDetail : rentalInfo.getRentalDetails()) {
      result.append("\t").append(rentalDetail.getMovie().getTitle()).append("\t").append(rentalDetail.getMovieRentalAmount()).append("\n");
    }

    result.append("Amount owed is ").append(rentalInfo.getTotalAmount()).append("\n");
    result.append("You earned ").append(rentalInfo.getTotalFrequentPoints()).append(" frequent points\n");
    return result.toString();
  }

  public RentalInfo saveRentalInfo(RentalInfo rentalInfo) {
    Customer customer = rentalInfo.getCustomer();

    double totalAmount = 0;
    int frequentRenterPoints = 0;

    List<RentalDetail> rentalDetails = new ArrayList<>();
    for (RentalDetail rentalDetail : rentalInfo.getRentalDetails()) {
      Movie movie = rentalDetail.getMovie();
      int rentalDays = rentalDetail.getMovieRentalDays();

      double thisAmount = calculateAmount(movie, rentalDays);
      frequentRenterPoints += calculateFrequentRenterPoints(movie, rentalDays);

      totalAmount += thisAmount;

      RentalDetail detail = new RentalDetail();
      detail.setMovie(movie);
      detail.setMovieRentalDays(rentalDays);
      detail.setMovieRentalAmount(thisAmount);
      rentalDetails.add(detail);
    }
    RentalInfo rental = new RentalInfo(null,customer,totalAmount,frequentRenterPoints,rentalDetails);
    rental = rentalInfoRepository.save(rental);
    return rental;
  }

  private double calculateAmount(Movie movie, int daysRented) {
    double amount = movie.getMovieType().getDefaultRentalPrice();
    if (daysRented > movie.getMovieType().getMaxRentalDays() && movie.getMovieType().getMaxRentalDays() > 0) {
      amount += (daysRented - movie.getMovieType().getMaxRentalDays()) * movie.getMovieType().getRentalPriceMultiplier();
    } else {
      amount = movie.getMovieType().getDefaultRentalPrice() * daysRented;
    }
    return amount;
  }

  private int calculateFrequentRenterPoints(Movie movie, int daysRented) {
    int points = 1;
    if (movie.getMovieType().getFrequentEnterPointsBonus() > 0 && daysRented > movie.getMovieType().getFrequentEnterPointsDays()) {
      points += movie.getMovieType().getFrequentEnterPointsBonus();
    }
    return points;
  }
}
