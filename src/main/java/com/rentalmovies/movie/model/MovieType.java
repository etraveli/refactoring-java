package com.rentalmovies.movie.model;

import com.rentalmovies.rentalpricingstrategy.ChildrenMoviePricingStrategy;
import com.rentalmovies.rentalpricingstrategy.NewReleaseMoviePricingStrategy;
import com.rentalmovies.rentalpricingstrategy.RegularMoviePricingStrategy;
import com.rentalmovies.rentalpricingstrategy.RentalPricingStrategy;
import com.rentalmovies.utils.MovieUtils;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * The MovieType enum represents the various types of movies available
 * for rent, each associated with a specific pricing strategy. This enum
 * encapsulates the creation and management of different pricing strategies,
 * promoting cleaner code and separation of concerns.
 *
 * <p>Each enum constant is associated with a specific implementation of
 * the RentalPricingStrategy, ensuring that each type of movie has its own
 * pricing logic, in adherence with the Strategy Design Pattern.</p>
 *
 * @author Sajid Riaz
 */
public enum MovieType
{
    REGULAR(RegularMoviePricingStrategy::new),
    CHILDREN(ChildrenMoviePricingStrategy::new),
    NEW(NewReleaseMoviePricingStrategy::new);

    private final RentalPricingStrategy strategy;

    MovieType(Supplier<RentalPricingStrategy> strategySupplier)
    {
        strategy = strategySupplier.get();
    }

    public RentalPricingStrategy getStrategy()
    {
        return strategy;
    }

    public static MovieType fromString(String movieType)
    {
        MovieUtils.validateString(movieType);
        return Arrays.stream(MovieType.values())
                .filter(type -> type.name().equalsIgnoreCase(movieType.trim()))
                .findFirst()
                .orElse(null); // or throw an exception or return a default value
    }
}
