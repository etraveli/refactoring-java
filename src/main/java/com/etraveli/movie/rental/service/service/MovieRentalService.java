package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.MovieRental;

import java.util.List;

public interface MovieRentalService {
    List<MovieRental> getRentalById(Long customerId);
    double calculateTotalRentalAmount(List<MovieRental> rentals);
    int calculateTotalLoyaltyPoints(List<MovieRental> rentals);
}
