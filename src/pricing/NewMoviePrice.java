package pricing;

public class NewMoviePrice implements Price {

  @Override
  public double getPriceBasedOnCategory(int days) {
    return days * 3;
  }

}