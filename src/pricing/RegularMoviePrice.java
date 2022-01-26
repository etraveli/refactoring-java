package pricing;

public class RegularMoviePrice implements Price {

  @Override
  public double getPriceBasedOnCategory(int days) {
    double baseprice = 2;

    if (days > 2) {
      return ((days - 2) * 1.5) + baseprice;
    }

    return baseprice;
  }

}