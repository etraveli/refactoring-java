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
}
