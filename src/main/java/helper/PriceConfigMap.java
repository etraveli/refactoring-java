package helper;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;
import model.MovieType;

@UtilityClass
public class PriceConfigMap {

  private Map<MovieType, PriceConfig> priceConfigMap = new HashMap<>();
  static {
    priceConfigMap.put(MovieType.REGULAR, PriceConfig.builder().basePriceDays(2).basePrice(2)
        .pricePerDay(1.5).build());
    priceConfigMap.put(MovieType.NEW, PriceConfig.builder().basePriceDays(0).basePrice(0)
        .pricePerDay(3).build());
    priceConfigMap.put(MovieType.CHILDREN, PriceConfig.builder().basePriceDays(3).basePrice(1.5)
        .pricePerDay(1.5).build());
  }

  public PriceConfig getPriceConfig(MovieType type) {
    return priceConfigMap.get(type);
  }

}
