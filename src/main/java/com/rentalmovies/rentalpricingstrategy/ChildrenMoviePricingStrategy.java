package com.rentalmovies.rentalpricingstrategy;

public final class ChildrenMoviePricingStrategy implements RentalPricingStrategy
{
    private static final double INITIAL_RENTAL_PRICE = 1.5;
    private static final int INITIAL_RENTAL_DAYS = 3;
    private static final double ADDITIONAL_DAY_PRICE = 1.5;

    @Override
    public double calculateRentalAmount(int daysRented)
    {
        if (isInitialRentalPeriod(daysRented))
        {
            return INITIAL_RENTAL_PRICE;
        }
        return calculateExtendedRentalPrice(daysRented);
    }

    private boolean isInitialRentalPeriod(int daysRented)
    {
        return daysRented <= INITIAL_RENTAL_DAYS;
    }

    private double calculateExtendedRentalPrice(int daysRented)
    {
        int additionalDays = daysRented - INITIAL_RENTAL_DAYS;
        double additionalCost = additionalDays * ADDITIONAL_DAY_PRICE;
        return INITIAL_RENTAL_PRICE + additionalCost;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented)
    {
        return DEFAULT_FREQUENT_RENTER_POINTS;
    }
}
