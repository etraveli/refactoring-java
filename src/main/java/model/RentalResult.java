package model;

import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalResult {
  private String customerName;
  private double totalAmount;
  private int bonusPoints;
  private List<MovieResult> results;

  @Override
  public String toString() {
    StringBuilder summary = new StringBuilder("Rental Record for " + customerName + "\n");
    results.forEach( result -> {
      if (Objects.nonNull(result.getMovieTitle())) {
        summary.append("\t").append(result.getMovieTitle())
            .append("\t").append(result.getAmount()).append("\n");
      } else {
        summary.append("\t" + "No movies found for id: ").append(result.getMovieId()).append("\n");
      }
    });
    summary.append("Amount owed is ").append(totalAmount).append("\n");
    summary.append("You earned ").append(bonusPoints).append(" frequent points\n");
    return summary.toString();
  }
}
