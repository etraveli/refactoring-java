package movie.service.impl;

import movie.dto.*;
import movie.service.CalculateAmountForMovieService;
import movie.service.MoviesPopulatorService;
import movie.service.RentalInfoService;
import movie.service.StatementOutputService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RentalInfoServiceImpl implements RentalInfoService {

    private MoviesPopulatorService moviesPopulatorService = new DummyMoviesPopulatorService();

    private CalculateAmountForMovieService calculateAmountForMovieService = new CalculateAmountForMovieService();

    @Override
    public String getStatementForCustomer(Customer customer) {

        HashMap<String, Movie> movies = moviesPopulatorService.populateMovies();

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        Map<String, Double> moviesAmount = new LinkedHashMap<>();

        for (MovieRental movieRental : customer.getRentals()) {
            double thisAmount = calculateAmountForMovieService.calculateAmountForMovie(movies, movieRental);
            moviesAmount.put(movies.get(movieRental.getMovieId()).getTitle(), thisAmount);
            frequentEnterPoints = getFrequentEnterPoints(frequentEnterPoints, movieRental, movies.get(movieRental.getMovieId()).getCode());

            totalAmount += thisAmount;
        }

        return StatementOutputService.createStringStatement(new RentalStatement(totalAmount, frequentEnterPoints, moviesAmount),
                customer.getName());
    }

    private int getFrequentEnterPoints(int frequentEnterPoints, MovieRental movieRental, String movieCode) {
        //add frequent bonus points
        frequentEnterPoints++;
        // add bonus for a two day new release rental
        if (MovieCodes.NEW.key.equals(movieCode) && movieRental.getDays() > 2) frequentEnterPoints++;
        return frequentEnterPoints;
    }

}
