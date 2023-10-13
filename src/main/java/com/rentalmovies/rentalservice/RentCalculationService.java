package com.rentalmovies.rentalservice;

import com.rentalmovies.models.MovieRental;
import com.rentalmovies.moviestore.MovieStore;
import com.rentalmovies.rentalpricingstrategy.RentalPricingStrategy;

import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * Decouple Rental calculation logic from model objects
 */
public class RentCalculationService
{
    public double calculateTotalAmount(List<MovieRental> rentals, MovieStore movieStore)
    {
        return calculateTotal(rentals, movieStore,
                rental -> getPricingStrategy(rental, movieStore)
                        .calculateRentalAmount(rental.getRentalDays()));
    }

    public int calculateTotalFrequentRenterPoints(List<MovieRental> rentals, MovieStore movieStore)
    {
        return (int) calculateTotal(rentals, movieStore,
                rental -> getPricingStrategy(rental, movieStore)
                        .getFrequentRenterPoints(rental.getRentalDays()));
    }

    private double calculateTotal(List<MovieRental> rentals,
                                  MovieStore movieStore,
                                  ToDoubleFunction<MovieRental> calculationFunction)
    {
        return rentals.stream()
                .mapToDouble(calculationFunction)
                .sum();
    }

    private RentalPricingStrategy getPricingStrategy(MovieRental rental, MovieStore movieStore)
    {
        return movieStore.getMovie(rental.getMovieId()).getMovieType().getStrategy();
    }
}
