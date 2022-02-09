package refactoring.java;

import refactoring.java.model.Customer;
import refactoring.java.model.Movie;
import refactoring.java.model.MovieRental;

public class RentalInfo {

  public String statement(Customer customer) {
    MovieRepository movies = new MovieRepository();
    PriceCalculator priceCalculator = new PriceCalculator();
    double totalAmount = 0;
    int frequentEnterPoints = 0;

    String result = "Rental Record for " + customer.getName() + "\n";

    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;
      Movie movie = movies.findById(r.getMovieId());

      if (movie == null) {
        System.err.println("Movie with id =" + r.getMovieId() + " does not exist.");
        continue;
      }

      PriceCalculationResult priceCalculationResult = priceCalculator.computePriceAndPoints(movie.getCategory(), r.getDays());

      thisAmount = priceCalculationResult.getPrice();
      frequentEnterPoints += priceCalculationResult.getPoints();

      //print figures for this rental
      result += "\t" + movies.findById(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }

    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
