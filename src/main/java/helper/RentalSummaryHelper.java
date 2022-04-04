package helper;

import handler.RentalStatementHandler;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import model.RentalResult;
import org.apache.log4j.Logger;

@UtilityClass
public class RentalSummaryHelper {
  private static final Logger logger = Logger.getLogger(RentalSummaryHelper.class);

  public static String displaySummary(RentalResult rentalResult) {
    logger.debug("Displaying summary for result: "+ rentalResult);
    StringBuilder summary = new StringBuilder(
        "Rental Record for " + rentalResult.getCustomerName() + "\n");
    rentalResult.getResults().forEach(result -> {
      if (Objects.nonNull(result.getMovieTitle())) {
        summary.append("\t").append(result.getMovieTitle())
            .append("\t").append(result.getAmount()).append("\n");
      } else {
        summary.append("\t" + "No movies found for id: ").append(result.getMovieId()).append("\n");
      }
    });
    summary.append("Amount owed is ").append(rentalResult.getTotalAmount()).append("\n");
    summary.append("You earned ").append(rentalResult.getBonusPoints())
        .append(" frequent points\n");
    return summary.toString();
  }
}
