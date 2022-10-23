package movie.code;

import movie.code.exceptions.MovieCodeNotFoundException;
import movie.code.exceptions.MovieCodeInstantiationException;

abstract public class MovieCode {
    protected String name;
    protected int minChargeDays;
    protected double initialChargingFactor;
    protected double continualChargingFactor;

    abstract public boolean hasExtraBonusPoint(int days);

    public double calculateAmount(int days) {
        double amount = minChargeDays * initialChargingFactor;
        if (days > minChargeDays) {
            amount = amount + (days - minChargeDays) * continualChargingFactor;
        }
        return amount;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public static MovieCode build(String codeName) throws MovieCodeNotFoundException,
            MovieCodeInstantiationException {
        Codes code;
        try {
            code = Codes.valueOf(codeName.strip().toUpperCase());
        } catch (IllegalArgumentException exc) {
            throw new MovieCodeNotFoundException("No MovieCode found to match '" + codeName + "'");
        }
        try {
            return code.getMovieCodeClass().getConstructor().newInstance();
        } catch (Exception exc) {
            throw new MovieCodeInstantiationException(exc.getMessage());
        }
    }
}
