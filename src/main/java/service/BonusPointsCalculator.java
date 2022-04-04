package service;

import dao.MovieRepo;
import javax.inject.Inject;
import javax.inject.Singleton;
import model.MovieRental;
import model.MovieType;
import validator.Validator;

@Singleton
public class BonusPointsCalculator {

  private final MovieRepo movieRepo;
  private static final int NEW_RELEASE_EXTRA_BONUS = 2;

  @Inject
  public BonusPointsCalculator(MovieRepo movieRepo) {
    this.movieRepo = movieRepo;
  }

  public int calculate(MovieRental movieRental, int bonusPoints) {
    var movie = movieRepo.getMovieById(movieRental.getMovieId());
    if (Validator.isValidMovie(movie)) {
      //add frequent bonus points
      bonusPoints++;
      // add bonus for a two day new release rental
      if (MovieType.NEW.equals(movie.getType()) && movieRental.getDays() > NEW_RELEASE_EXTRA_BONUS) {
        bonusPoints++;
      }
    }
    return bonusPoints;
  }

}
