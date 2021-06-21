package service;

import model.Customer;
import model.Movie;
import model.MovieRental;
import model.RentalCategory;
import repository.MovieRepository;

public class RentalService {

  private static final int ENTER_POINTS_DAY_LIMIT = 2;
  private static final int DEFAULT_ENTER_POINTS = 1;
  private static final int BONUS_ENTER_POINTS = 1;

  private MovieRepository movieRepository;

  public RentalService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public String statement(Customer customer) {
    if (customer == null) return "No customer provided.";
    if (customer.getRentals() == null) return "No customer rentals provided.";

    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
    double totalAmount = 0;
    int frequentEnterPoints = 0;

    for(MovieRental rental : customer.getRentals()) {
      if (!movieRepository.exists(rental.getMovieId())) {
        result.append("No movie with ID ").append(rental.getMovieId()).append(" found.\n");
        continue;
      }

      Movie movie = movieRepository.findById(rental.getMovieId());
      int rentalDays = rental.getDays();
      double rentalCost = movie.getRentalCategory().getCost(rentalDays);
      result.append("\t").append(movie.getTitle()).append("\t").append(rentalCost).append("\n");

      totalAmount += rentalCost;
      frequentEnterPoints += getRentalEnterPoints(movie, rentalDays);
    }

    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }

  private int getRentalEnterPoints(Movie movie, int rentalDays) {
    int bonusPoints = (movie.getRentalCategory().equals(RentalCategory.NEW) && rentalDays > ENTER_POINTS_DAY_LIMIT)
            ? BONUS_ENTER_POINTS
            : 0;
    return DEFAULT_ENTER_POINTS + bonusPoints;
  }

}
