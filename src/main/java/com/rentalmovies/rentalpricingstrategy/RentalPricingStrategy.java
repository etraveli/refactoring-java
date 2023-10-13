package com.rentalmovies.rentalpricingstrategy;

/**
 * Open/Closed Principle. Strategy Pattern for calculating rental amount and frequent renter points
 * based on movie category. Easy to add new pricing algorithms without modifying existing code.
 * This makes adding new movie types/categories easier without modifying the existing code.
 * It allows to encapsulate the pricing algorithm and easily switch between different pricing strategies. 
 */
public interface RentalPricingStrategy
{
    int DEFAULT_FREQUENT_RENTER_POINTS = 1;
    double DEFAULT_RENTAL_AMOUNT = 0.0;

    default double calculateRentalAmount(int daysRented)
    {
        return DEFAULT_RENTAL_AMOUNT;
    };

    default int getFrequentRenterPoints(int daysRented)
    {
        return DEFAULT_FREQUENT_RENTER_POINTS;
    };
}
