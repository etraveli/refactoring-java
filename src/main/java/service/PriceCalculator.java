package service;

import static model.MovieType.CHILDREN;
import static model.MovieType.NEW;
import static model.MovieType.REGULAR;

import dao.MovieRepo;
import helper.PriceConfig;
import helper.PriceConfigMap;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Singleton;
import model.MovieRental;
import model.MovieResult;
import org.apache.log4j.Logger;

@Singleton
public class PriceCalculator {
  private static final Logger logger = Logger.getLogger(PriceCalculator.class);
  private final MovieRepo movieRepo;

  @Inject
  public PriceCalculator(MovieRepo movieRepo) {
    this.movieRepo = movieRepo;
  }

  public MovieResult calculate(MovieRental movieRental) {
    //Get movie from movie Repo
    var movie = movieRepo.getMovieById(movieRental.getMovieId());
    if (Objects.isNull(movie)) {
      logger.warn("No movies found for id: " + movieRental.getMovieId());
      return MovieResult.builder().movieId(movieRental.getMovieId()).build();
    }
    double amount;
    PriceConfig config;
    //Combine Regular and Children types as it has same calculation logic
    if (REGULAR.equals(movie.getType()) || CHILDREN.equals(movie.getType())) {
      config = PriceConfigMap.getPriceConfig(movie.getType());
      amount = config.getBasePrice();
      if (movieRental.getDays() > config.getBasePriceDays()) {
        amount += (movieRental.getDays() - config.getBasePriceDays()) * config.getPricePerDay();
      }
    } else if (NEW.equals(movie.getType())) {
      config = PriceConfigMap.getPriceConfig(movie.getType());
      amount = movieRental.getDays() * config.getPricePerDay();
    } else {
      throw new IllegalArgumentException("Invalid type in price calculation");
    }
    return MovieResult.builder().movieTitle(movie.getTitle()).amount(amount).build();
  }
}
