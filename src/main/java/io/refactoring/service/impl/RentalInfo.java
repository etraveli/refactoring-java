package io.refactoring.service.impl;

import io.refactoring.model.Customer;
import io.refactoring.model.MovieCode;
import io.refactoring.model.MovieRental;
import io.refactoring.repository.IMoviesRentalRepository;
import io.refactoring.service.IRentalCalculator;
import io.refactoring.service.IRentalInfo;

import static io.refactoring.constants.AppConstants.NEXTLINE;
import static io.refactoring.constants.AppConstants.TAB;

public class RentalInfo implements IRentalInfo {

    private IRentalCalculator rentalCalculator;
    private IMoviesRentalRepository moviesRentalRepository;

    public RentalInfo(IRentalCalculator rentalCalculator, IMoviesRentalRepository moviesRentalRepository){
        this.rentalCalculator = rentalCalculator;
        this.moviesRentalRepository = moviesRentalRepository;
    }

    @Override
    public String statement(Customer customer) {
        StringBuilder rentalInfoBuilder = new StringBuilder("Rental Record for ").append(customer.name()).append(NEXTLINE);
        double totalAmount = 0;
        int frequentEnterPoints = 0;

        for (MovieRental rental : customer.rentals()) {
            var rent = rentalCalculator.rent(rental);
            totalAmount += rent;
            frequentEnterPoints++;
            // add bonus for a two-day new release rental
            if (moviesRentalRepository.movie(rental.movieId()).code().equals(MovieCode.NEW) && rental.days() > 2)
                frequentEnterPoints++;
            rentalInfoBuilder
                    .append(TAB)
                    .append(moviesRentalRepository.movie(rental.movieId()).title())
                    .append(TAB)
                    .append(rent)
                    .append(NEXTLINE);
        }

        return rentalInfoBuilder
                .append("Amount owed is ")
                .append(totalAmount)
                .append(NEXTLINE)
                .append("You earned ")
                .append(frequentEnterPoints)
                .append(" frequent points")
                .append(NEXTLINE)
                .toString();
    }
}
