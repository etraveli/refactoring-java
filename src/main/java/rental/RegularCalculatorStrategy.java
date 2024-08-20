package main.java.rental;

import main.java.movie.MovieRental;

public class RegularCalculatorStrategy implements IRentalCalculatorStrategy {

    private static final double BASE_AMOUNT = 2.0;
    private static final int BASE_DAYS = 2;
    private static final double EXTRA_DAY_RATE = 1.5;

    @Override
    public double calculateAmount(MovieRental rental) {
        double amount = BASE_AMOUNT;
        int extraDays = rental.getDays() - BASE_DAYS;

        if (extraDays > 0) {
            amount += calculateExtraAmount(extraDays);
        }

        return amount;
    }

    private double calculateExtraAmount(int extraDays) {
        return extraDays * EXTRA_DAY_RATE;
    }
}