package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.MovieRental;
import com.etraveli.movie.rental.service.repositories.MovieRentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieRentalServiceImpl implements MovieRentalService {
    private final MovieRentalRepository movieRentalRepository;
    private final CustomerService customerService;

    @Override
    public List<MovieRental> getRentalById(Long customerId) {
        return movieRentalRepository.findByCustomerId(customerId);
    }

    @Override
    public double calculateTotalRentalAmount(List<MovieRental> rentals) {
        return rentals.stream().mapToDouble(rental -> rental.getRentalAmount().doubleValue()).sum();
    }

    @Override
    public int calculateTotalLoyaltyPoints(List<MovieRental> rentals) {
        return 0;
    }
}
