package movie.code;

public class MovieCodeChildrens extends MovieCode {

  public MovieCodeChildrens() {
    this.name = Codes.CHILDRENS.name();
    this.minChargeDays = 3;
    this.initialChargingFactor = 1.5;
    this.continualChargingFactor = 1.5;
  }

  @Override
  public boolean hasExtraBonusPoint(int days) {
    return false;
  }
}
