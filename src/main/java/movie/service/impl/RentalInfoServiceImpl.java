package movie.service.impl;

import movie.dto.*;
import movie.persistence.MovieRentalRepository;
import movie.service.CalculateAmountForMovieService;
import movie.service.RentalInfoService;
import movie.service.StatementOutputService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class RentalInfoServiceImpl implements RentalInfoService {

    private MovieRentalRepository movieRentalRepository = new MovieRentalRepository();

    private CalculateAmountForMovieService calculateAmountForMovieService = new CalculateAmountForMovieService();

    @Override
    public String getStatementForCustomer(Customer customer) {

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        Map<String, Double> moviesAmount = new LinkedHashMap<>();

        for (MovieRental movieRental : customer.getRentals()) {
            Optional<Movie> possibleMovie = movieRentalRepository.getMovieById(movieRental.getMovieId());

            if (possibleMovie.isPresent()) {
                double thisAmount = calculateAmountForMovieService.calculateAmountForMovie(possibleMovie.get(), movieRental);
                moviesAmount.put(possibleMovie.get().getTitle(), thisAmount);
                frequentEnterPoints = getFrequentEnterPoints(frequentEnterPoints, movieRental, possibleMovie.get().getCode());

                totalAmount += thisAmount;
            }

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
