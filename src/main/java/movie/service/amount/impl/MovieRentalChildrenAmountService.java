package movie.service.amount.impl;

import movie.dto.MovieRental;
import movie.service.amount.MovieRentalAmountService;

public class MovieRentalChildrenAmountService implements MovieRentalAmountService {
    @Override
    public double getAmountForMovieRental(MovieRental movieRental) {
        double thisAmount = 1.5;
        if (movieRental.getDays() > 3) {
            return ((movieRental.getDays() - 3) * 1.5) + thisAmount;
        }

        return thisAmount;
    }
}
