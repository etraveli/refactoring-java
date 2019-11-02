package movie.service;

import movie.dto.Movie;
import movie.dto.MovieRental;
import movie.service.amount.MovieRentalAmountFactory;

import java.util.HashMap;

public class CalculateAmountForMovieService {

    public double calculateAmountForMovie(HashMap<String, Movie> movies, MovieRental movieRental){
        return MovieRentalAmountFactory.getServiceForMovie(movies, movieRental).getAmountForMovieRental(movieRental);
    }
}
