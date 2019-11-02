package movie.service.amount.impl;

import movie.dto.MovieRental;
import movie.service.amount.MovieRentalAmountService;

public class NotImplementedAmountService implements MovieRentalAmountService {

    @Override
    public double getAmountForMovieRental(MovieRental movieRental) {
        throw new UnsupportedOperationException("this movie is not supported " + movieRental.getMovieId());
    }
}
