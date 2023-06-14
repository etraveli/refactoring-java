package io.refactoring.service.impl;

import io.refactoring.model.MovieRental;
import io.refactoring.repository.IMoviesRentalRepository;
import io.refactoring.service.IRentalCalculator;

public class RentalCalculator implements IRentalCalculator {

    private IMoviesRentalRepository moviesRentalRepository;

    public RentalCalculator(IMoviesRentalRepository moviesRentalRepository){
        this.moviesRentalRepository = moviesRentalRepository;
    }

    @Override
    public double rent(MovieRental rental){
        switch (moviesRentalRepository.movie(rental.movieId()).code()) {
            case REGULAR -> {
                return regularMovieRent(rental.days());
            }
            case NEW -> {
                return newMovieRent(rental.days());
            }
            case CHILDREN -> {
                return childrenMovieRent(rental.days());
            }
            default -> throw new RuntimeException("Movie type not found");
        }
    }

    private double regularMovieRent(int days) {
        double rent = 2;
        if (days > 2) rent = rent + ((days - 2) * 1.5);
        return rent;
    }

    private double childrenMovieRent(int days) {
        double rent = 1.5;
        if (days > 3) rent = rent + ((days - 3) * 1.5);
        return rent;
    }

    private double newMovieRent(int days) {
        return days * 3;
    }

}
