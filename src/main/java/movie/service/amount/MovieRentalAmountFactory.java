package movie.service.amount;

import movie.dto.Movie;
import movie.dto.MovieCodes;
import movie.dto.MovieRental;
import movie.service.amount.impl.MovieRentalChildrenAmountService;
import movie.service.amount.impl.MovieRentalNewAmountService;
import movie.service.amount.impl.MovieRentalRegularAmountService;
import movie.service.amount.impl.NotImplementedAmountService;

import java.util.HashMap;

public class MovieRentalAmountFactory {

    private static MovieRentalNewAmountService movieRentalNewAmountService = new MovieRentalNewAmountService();
    private static MovieRentalChildrenAmountService movieRentalChildrenAmountService = new MovieRentalChildrenAmountService();
    private static MovieRentalRegularAmountService movieRentalRegularAmountService = new MovieRentalRegularAmountService();
    private static NotImplementedAmountService notImplementedAmountService = new NotImplementedAmountService();

    public static MovieRentalAmountService getServiceForMovie(HashMap<String, Movie> movies, MovieRental movieRental) {

        if (MovieCodes.REGULAR.key.equals(movies.get(movieRental.getMovieId()).getCode())) {
            return movieRentalRegularAmountService;
        }

        if (MovieCodes.NEW.key.equals(movies.get(movieRental.getMovieId()).getCode())) {
            return movieRentalNewAmountService;
        }

        if (MovieCodes.CHILDRENS.key.equals(movies.get(movieRental.getMovieId()).getCode())) {
            return movieRentalChildrenAmountService;
        }

        return notImplementedAmountService;
    }
}
