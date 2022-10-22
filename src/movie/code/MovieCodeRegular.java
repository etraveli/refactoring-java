package movie.code;

public class MovieCodeRegular extends MovieCode {

  public MovieCodeRegular() {
    this.name = Codes.REGULAR.name();
    this.minChargeDays = 2;
    this.initialChargingFactor = 1.0;
    this.continualChargingFactor = 1.5;
  }

  @Override
  public boolean hasExtraBonusPoint(int days) {
    return false;
  }
}
