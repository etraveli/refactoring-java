import models.Customer;
import models.MovieRental;

public class RentalInfo {

  private BasicRentCalculator basicRentCalculator = new BasicRentCalculator();
  private FrequentEnterPointsCalculator frequentEnterPointsCalculator = new FrequentEnterPointsCalculator();

  public String statement(Customer customer) {

    MovieLibrary movieLibrary = new MovieLibrary();

    double totalAmount = 0;
    int totalFrequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental movieRental : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      //TODO extract this to strategy pattern
      // Seems like the logic could be dependent on any property of movie model
      thisAmount = basicRentCalculator.calculateFromMovieRental(movieRental);

      totalFrequentEnterPoints += frequentEnterPointsCalculator.calculateFromMovieRental(movieRental);

      //print figures for this rental
      result += "\t" + movieLibrary.getMovieById(movieRental.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + totalFrequentEnterPoints + " frequent points\n";

    return result;
  }
}
