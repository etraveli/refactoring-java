package com.rentalmovies.rentalpricingstrategy;

public final class RegularMoviePricingStrategy implements RentalPricingStrategy
{
    private static final double STANDARD_RENTAL_FEE = 2.0;
    private static final int STANDARD_RENTAL_PERIOD = 2;
    private static final double EXTRA_DAY_FEE = 1.5;

    @Override
    public double calculateRentalAmount(int daysRented)
    {
        if (isWithinStandardRentalPeriod(daysRented))
        {
            return STANDARD_RENTAL_FEE;
        }
        return calculateExtendedRentalFee(daysRented);
    }

    private boolean isWithinStandardRentalPeriod(int daysRented)
    {
        return daysRented <= STANDARD_RENTAL_PERIOD;
    }

    private double calculateExtendedRentalFee(int daysRented)
    {
        int extraDays = daysRented - STANDARD_RENTAL_PERIOD;
        double extraFee = extraDays * EXTRA_DAY_FEE;
        return STANDARD_RENTAL_FEE + extraFee;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented)
    {
        return DEFAULT_FREQUENT_RENTER_POINTS;
    }
}
