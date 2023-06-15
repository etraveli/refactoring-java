package io.refactoring.service.impl;

import io.refactoring.model.MovieCode;
import io.refactoring.model.MovieRental;
import io.refactoring.repository.IMoviesRentalRepository;
import io.refactoring.service.IRentalCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RentalCalculator implements IRentalCalculator {

    private static final Logger log = LoggerFactory.getLogger(RentalCalculator.class);

    private IMoviesRentalRepository moviesRentalRepository;

    public RentalCalculator(IMoviesRentalRepository moviesRentalRepository){
        this.moviesRentalRepository = moviesRentalRepository;
    }

    @Override
    public double rent(MovieRental rental){
        MovieCode movieCode = moviesRentalRepository.movie(rental.movieId()).code();
        switch (movieCode) {
            case REGULAR -> {
                return regularMovieRent(rental.days());
            }
            case NEW -> {
                return newMovieRent(rental.days());
            }
            case CHILDREN -> {
                return childrenMovieRent(rental.days());
            }
            default -> {
                log.error("Movie type {} not found", movieCode.genre);
                throw new RuntimeException("Movie type not found");
            }
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
