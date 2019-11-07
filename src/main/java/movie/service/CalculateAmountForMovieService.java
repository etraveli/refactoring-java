package movie.service;

import movie.dto.Movie;
import movie.dto.MovieRental;
import movie.service.amount.MovieRentalAmountFactory;

public class CalculateAmountForMovieService {

    public double calculateAmountForMovie(Movie movie, MovieRental movieRental) {
        return MovieRentalAmountFactory.getServiceForMovieCode(movie.getCode()).getAmountForMovieRental(movieRental);
    }
}
