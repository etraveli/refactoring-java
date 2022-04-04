package handler;

import helper.RentalSummaryHelper;
import java.util.Objects;
import javax.inject.Inject;
import model.Customer;
import org.apache.log4j.Logger;
import service.RentalService;
import validator.Validator;

public class RentalStatementHandler {
  private static final Logger logger = Logger.getLogger(RentalStatementHandler.class);

  private RentalService rentalService;

  @Inject
  public RentalStatementHandler(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  public String generateSummary(Customer customer) {
    Validator.isValidCustomer(customer);
    logger.debug("Request for rental statement for customer: " + customer.getName());
    var rentalResult = rentalService.getStatement(customer);
    return RentalSummaryHelper.displaySummary(rentalResult);
  }

}
