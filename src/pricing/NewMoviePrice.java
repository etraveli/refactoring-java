package pricing;

public class NewMoviePrice implements Price {

  @Override
  public double getPriceFor(int days) {
    return days * 3;
  }

}