package service;

import dao.MovieRepo;
import helper.PriceConfigMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import model.MovieRental;
import model.MovieResult;
import org.apache.log4j.Logger;
import validator.Validator;

@Singleton
public class PriceCalculator {
  private static final Logger logger = Logger.getLogger(PriceCalculator.class);
  private final MovieRepo movieRepo;

  @Inject
  public PriceCalculator(MovieRepo movieRepo) {
    this.movieRepo = movieRepo;
  }

  public MovieResult calculate(MovieRental movieRental) {
    logger.debug("Movie Rental: "+ movieRental);
    //Get movie from movie Repo
    var movie = movieRepo.getMovieById(movieRental.getMovieId());
    if(!Validator.isValidMovie(movie)) {
      logger.warn("No movies found for id: " + movieRental.getMovieId());
      return MovieResult.builder().movieId(movieRental.getMovieId()).build();
    }

    var config = PriceConfigMap.getPriceConfig(movie.getType());
    double amount = config.getBasePrice();
    int daysToCalculate = (movieRental.getDays() - config.getBasePriceDays()) > 0 ?
        movieRental.getDays() - config.getBasePriceDays() : movieRental.getDays();

    if (movieRental.getDays() > config.getBasePriceDays()) {
      amount += daysToCalculate * config.getPricePerDay();
    }
    logger.debug("Price for Movie Rental: "+ movieRental + "is :" + amount);
    return MovieResult.builder().movieTitle(movie.getTitle()).amount(amount).build();
  }
}
