package movie.service.amount.impl;

import movie.dto.MovieRental;
import movie.service.amount.MovieRentalAmountService;

public class MovieRentalRegularAmountService implements MovieRentalAmountService {
    @Override
    public double getAmountForMovieRental(MovieRental movieRental) {
        double thisAmount = 2;
        if (movieRental.getDays() > 2) {
            return ((movieRental.getDays() - 2) * 1.5) + thisAmount;
        }
        return thisAmount;
    }
}
