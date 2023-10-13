package com.rentalmovies.models;

import com.rentalmovies.rentalpricingstrategy.RentalPricingStrategy;
import static com.rentalmovies.utils.MovieUtils.validateString;

import java.util.Objects;

/**
 * Making a class immutable should be a priority, especially in environments where concurrency, security, and data consistency are paramount.
 * Use final keyword with classes when possible to ensure they cannot be subclassed, adding an extra layer of immutability.
 * Ensuring classes are immutable where possible to enhance thread safety and data integrity
 * Validation: Added validation to ensure that we have non-null, valid data to work with, making the system more robust.
 *
 * Encapsulated movie data within the Movie class, making the code more object-oriented and easier to manage.
 */
public final class Movie
{
    private final String title;
    private final RentalPricingStrategy pricingStrategy;

    public Movie(final String title, final RentalPricingStrategy pricingStrategy)
    {
        this.title = validateString(title);
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy, "Invalid pricing strategy");
    }

    public String getTitle()
    {
        return title;
    }

    public double calculateRentalAmount(int daysRented)
    {
        return pricingStrategy.calculateRentalAmount(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented)
    {
        return pricingStrategy.getFrequentRenterPoints(daysRented);
    }

    @Override
    public String toString()
    {
        return String.format("Movie{title='%s', code='%s'}", title, pricingStrategy);
    }
}
