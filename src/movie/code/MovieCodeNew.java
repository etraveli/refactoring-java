package movie.code;

public class MovieCodeNew extends MovieCode {

    public MovieCodeNew() {
        this.name = Codes.NEW.name();
        this.minChargeDays = 0;
        this.initialChargingFactor = 0;
        this.continualChargingFactor = 3;
    }

    @Override
    public boolean hasExtraBonusPoint(int days) {
        return days > 2;
    }
}
