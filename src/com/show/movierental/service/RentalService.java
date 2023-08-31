package com.show.movierental.service;

import com.show.movierental.core.Customer;
import com.show.movierental.core.Movie;
import com.show.movierental.core.Rental;
import com.show.movierental.repository.MovieRepository;

public class RentalService {
    private MovieRepository movieRepository;

    public RentalService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String generateRentalStatement(Customer customer) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

        for (Rental rental : customer.getRentals()) {
            Movie movie = movieRepository.findMovieById(rental.getMovieId());
            double thisAmount = movie.calculatePrice(rental.getDays());

            // Add frequent bonus points
            frequentRenterPoints += movie.calculateFrequentRentalPoints(rental.getDays());

            // Print rental details
            result.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }

        // Add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent points\n");

        return result.toString();
    }

}
