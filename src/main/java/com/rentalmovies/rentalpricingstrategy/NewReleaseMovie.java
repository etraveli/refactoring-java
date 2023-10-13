package com.rentalmovies.rentalpricingstrategy;

public final class NewReleaseMovie implements RentalPricingStrategy
{
    private static final double RENTAL_RATE_PER_DAY = 3.0;
    private static final int BONUS_RENTAL_DAYS_THRESHOLD = 2;
    private static final int BONUS_FREQUENT_RENTER_POINTS = 2;

    @Override
    public double calculateRentalAmount(int daysRented)
    {
        return daysRented * RENTAL_RATE_PER_DAY;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented)
    {
        return daysRented > BONUS_RENTAL_DAYS_THRESHOLD
                ? BONUS_FREQUENT_RENTER_POINTS
                : DEFAULT_FREQUENT_RENTER_POINTS;
    }
}
