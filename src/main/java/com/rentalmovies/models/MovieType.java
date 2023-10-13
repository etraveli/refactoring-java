package com.rentalmovies.models;

import com.rentalmovies.rentalpricingstrategy.ChildrenMoviePricingStrategy;
import com.rentalmovies.rentalpricingstrategy.NewReleaseMoviePricingStrategy;
import com.rentalmovies.rentalpricingstrategy.RegularMoviePricingStrategy;
import com.rentalmovies.rentalpricingstrategy.RentalPricingStrategy;

import java.util.function.Supplier;

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
}
