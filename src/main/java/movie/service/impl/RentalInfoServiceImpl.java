package movie.service.impl;

import movie.dto.Customer;
import movie.dto.Movie;
import movie.dto.MovieCodes;
import movie.dto.MovieRental;
import movie.service.CalculateAmountForMovieService;
import movie.service.MoviesPopulatorService;
import movie.service.RentalInfoService;

import java.util.HashMap;

public class RentalInfoServiceImpl implements RentalInfoService {

    private MoviesPopulatorService moviesPopulatorService = new DummyMoviesPopulatorService();
    private CalculateAmountForMovieService calculateAmountForMovieService = new CalculateAmountForMovieService();

    private HashMap<String, Movie> movies = moviesPopulatorService.populateMovies();

    @Override
    public String getStatementForCustomer(Customer customer) {

        double totalAmount = 0;
        int frequentEnterPoints = 0;

        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

        for (MovieRental movieRental : customer.getRentals()) {
            double thisAmount = calculateAmountForMovieService.calculateAmountForMovie(movies, movieRental);
            frequentEnterPoints = getFrequentEnterPoints(frequentEnterPoints, movieRental);

            //print figures for this rental
            result.append("\t").append(movies.get(movieRental.getMovieId()).getTitle()).append("\t").append(thisAmount).append("\n");

            totalAmount += thisAmount;
        }
        // add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

        return result.toString();
    }

    private int getFrequentEnterPoints(int frequentEnterPoints, MovieRental movieRental) {
        //add frequent bonus points
        frequentEnterPoints++;
        // add bonus for a two day new release rental
        if (MovieCodes.NEW.key.equals(movies.get(movieRental.getMovieId()).getCode()) && movieRental.getDays() > 2) frequentEnterPoints++;
        return frequentEnterPoints;
    }

}
