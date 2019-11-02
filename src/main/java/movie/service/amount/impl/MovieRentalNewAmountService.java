package movie.service.amount.impl;

import movie.dto.MovieRental;
import movie.service.amount.MovieRentalAmountService;

public class MovieRentalNewAmountService implements MovieRentalAmountService {
    @Override
    public double getAmountForMovieRental(MovieRental movieRental) {
        return movieRental.getDays() * 3;
    }
}
