import models.Customer;
import models.MovieRental;

public class RentalInfo {

  //All these values should be initialised and inject from config
  private BasicRentCalculator basicRentCalculator ;
  private FrequentEnterPointsCalculator frequentEnterPointsCalculator ;
  private RentalLedger rentalLedger ;

  public RentalInfo() {
    Provider provider = Provider.getInstance();
    basicRentCalculator = provider.getBasicRentCalculator();
    frequentEnterPointsCalculator = provider.getFrequentEnterPointsCalculator();
    rentalLedger = provider.getRentalLedger();
  }

  public String statement(Customer customer) {

    MovieLibrary movieLibrary = new MovieLibrary();

    double totalAmount = 0;
    int totalFrequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental movieRental : rentalLedger.getRentalsForCustomer(customer)) {
      double thisAmount = 0;
      // determine amount for each movie
      thisAmount = basicRentCalculator.calculateFromMovieRental(movieRental);
      //determine frequent enter points for each movie
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
