package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import model.Customer;
import model.MovieRental;
import model.MovieResult;
import model.RentalResult;
import org.apache.log4j.Logger;

public class RentalService {

  private static final Logger logger = Logger.getLogger(RentalService.class);

  private final PriceCalculator priceCalculator;
  private final BonusPointsCalculator bonusPointsCalculator;

  @Inject
  public RentalService(PriceCalculator priceCalculator, BonusPointsCalculator bonusPointsCalculator) {
    this.priceCalculator = priceCalculator;
    this.bonusPointsCalculator = bonusPointsCalculator;
  }

  /**
   *
   * @param customer customer object along with movie rental information
   * @return The Rental statement for all the movies rented by the customer
   */
  public RentalResult getStatement(Customer customer) {
    logger.info("Getting Rental statement");
    RentalResult rentalResult = RentalResult.builder().customerName(customer.getName())
        .build();
    List<MovieResult> movieResults = new ArrayList<>();
    double totalAmount = 0.0;
    int bonusPoints = 0;
    for (MovieRental movieRental : customer.getRentals()) {
      var movieResult = priceCalculator.calculate(movieRental);
      totalAmount += movieResult.getAmount();
      movieResults.add(movieResult);
      bonusPoints = bonusPointsCalculator.calculate(movieRental, bonusPoints);
    }
    rentalResult.setResults(movieResults);
    rentalResult.setTotalAmount(totalAmount);
    rentalResult.setBonusPoints(bonusPoints);
    return rentalResult;
  }
}
