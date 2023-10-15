package com.rentalmovies.service;

import com.rentalmovies.customer.model.CustomerResponseDTO;
import com.rentalmovies.exceptions.CustomerNotFoundException;
import com.rentalmovies.movie.model.MovieResponseDTO;
import com.rentalmovies.movierental.model.MovieRentalResponseDTO;
import com.rentalmovies.moviestore.MovieStoreDataAccess;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Decouple Rental calculation logic from model objects
 * The RentCalculationService class handles the calculations related to movie rentals,
 * including the rental amount and frequent renter points. It interacts with the
 * MovieStoreDataAccess to retrieve the necessary data for these calculations.
 *
 * immutable so thread safe
 * @author Sajid Riaz
 */
public final class RentCalculationService
{
    private final MovieStoreDataAccess movieStore;

    public RentCalculationService(final MovieStoreDataAccess movieStore)
    {
        this.movieStore = movieStore;
    }

    public List<MovieRentalSummary> getMovieRentalSummary(String customerId)
    {
        Optional<CustomerResponseDTO> customer = movieStore.getCustomerById(customerId);
        List<MovieRentalSummary> rentalResults = customer
                .orElseThrow(() -> new CustomerNotFoundException(customerId))
                .getRentals()
                .stream()
                .map(rental -> calculateRentalSummary(rental, customer.get().getName()))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(rentalResults);
    }

    private MovieRentalSummary calculateRentalSummary(MovieRentalResponseDTO rental, String customerName)
    {
        Optional<MovieResponseDTO> movie = movieStore.getMovieById(rental.getMovieId());
        MovieResponseDTO responseDTO = movie.get();
        double amount = responseDTO
                .getEnumMovieType(responseDTO.getMovieType())
                .getStrategy()
                .calculateRentalAmount(rental.getRentalDays());

        int frequentRenterPoints = responseDTO
                .getEnumMovieType(responseDTO.getMovieType())
                .getStrategy()
                .getFrequentRenterPoints(rental.getRentalDays());

        return new MovieRentalSummary(responseDTO, customerName, amount, frequentRenterPoints);
    }
}
