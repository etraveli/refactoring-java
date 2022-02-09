package refactoring.java;

import refactoring.java.model.Customer;
import refactoring.java.model.Movie;
import refactoring.java.model.MovieRental;

public class RentalInfo {

  public String createStatement(Customer customer) {
    MovieRepository movies = new MovieRepository();
    PriceCalculator priceCalculator = new PriceCalculator();
    double totalRentalAmount = 0;
    int loyaltyPoints = 0;

    StringBuilder statement = new StringBuilder();
    statement.append("Rental Record for ");
    statement.append(customer.getName());
    statement.append("\n");

    for (MovieRental rental : customer.getRentals()) {
      double rentalAmount = 0;
      Movie movie = movies.findById(rental.getMovieId());

      if (movie == null) {
        System.err.println("Movie with id =" + rental.getMovieId() + " does not exist.");
        continue;
      }

      PriceCalculationResult priceCalculationResult = priceCalculator.computePriceAndPoints(movie.getCategory(), rental.getDays());

      rentalAmount = priceCalculationResult.getPrice();
      loyaltyPoints += priceCalculationResult.getPoints();

      // Print figures for this rental
      statement.append("\t");
      statement.append(movie.getTitle());
      statement.append("\t");
      statement.append(rentalAmount);
      statement.append("\n");

      totalRentalAmount = totalRentalAmount + rentalAmount;
    }

    // Add footer lines
    statement.append("Amount owed is ");
    statement.append(totalRentalAmount);
    statement.append("\n");

    statement.append("You earned ");
    statement.append(loyaltyPoints);
    statement.append(" frequent points\n");

    return statement.toString();
  }
}
