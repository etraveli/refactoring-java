package helper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceConfig {
  private int basePriceDays;
  private double basePrice;
  private double pricePerDay;
}
