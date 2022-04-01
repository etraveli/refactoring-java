package helper;

import java.util.Objects;
import lombok.experimental.UtilityClass;
import model.RentalResult;

@UtilityClass
public class RentalSummaryHelper {

  public String displaySummary(RentalResult rentalResult) {
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
