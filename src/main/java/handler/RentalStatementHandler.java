package handler;

import helper.RentalSummaryHelper;
import java.util.Objects;
import javax.inject.Inject;
import model.Customer;
import org.apache.log4j.Logger;
import service.RentalService;

public class RentalStatementHandler {
  private static final Logger logger = Logger.getLogger(RentalStatementHandler.class);

  private RentalService rentalService;

  @Inject
  public RentalStatementHandler(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  public String generateSummary(Customer customer) {
    if (Objects.isNull(customer) || Objects.isNull(customer.getName())) {
      logger.warn("Invalid customer or name");
      throw new RuntimeException("Invalid Customer or customer name");
    }
    if (Objects.isNull(customer.getRentals())) {
      logger.warn("Invalid Movie Rentals or no movie Rentals");
      throw new RuntimeException("Invalid Movie Rentals or No movie Rentals");
    }
    var rentalResult = rentalService.getStatement(customer);
    return RentalSummaryHelper.displaySummary(rentalResult);
  }

}
