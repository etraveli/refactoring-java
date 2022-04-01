package service;

import dao.MovieRepo;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Singleton;
import model.MovieRental;
import model.MovieResult;
import model.MovieType;
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
    MovieResult movieResult = null;
    //Get movie from movie Repo
    var movie = movieRepo.getMovieById(movieRental.getMovieId());
    if (Objects.nonNull(movie)) {
      double thisAmount = 0;
      // determine amount for each movie
      if (MovieType.REGULAR.equals(movie.getType())) {
        thisAmount = 2;
        if (movieRental.getDays() > 2) {
          thisAmount = ((movieRental.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (MovieType.NEW.equals(movie.getType())) {
        thisAmount = movieRental.getDays() * 3;
      }
      if (MovieType.CHILDREN.equals(movie.getType())) {
        thisAmount = 1.5;
        if (movieRental.getDays() > 3) {
          thisAmount = ((movieRental.getDays() - 3) * 1.5) + thisAmount;
        }
      }
      movieResult =  MovieResult.builder().movieTitle(movie.getTitle()).amount(thisAmount).build();
    }
    else {
      logger.warn("No movies found for id: " + movieRental.getMovieId());
      movieResult = MovieResult.builder().movieId(movieRental.getMovieId()).build();
    }

    return movieResult;
  }
}
