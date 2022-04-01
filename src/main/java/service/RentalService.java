package service;

import dao.MovieRepo;
import javax.inject.Inject;
import model.Customer;
import model.MovieRental;
import model.MovieType;
import org.apache.log4j.Logger;

public class RentalService {

  private static final Logger logger = Logger.getLogger(RentalService.class);

  private final MovieRepo movieRepo;

  @Inject
  public RentalService(MovieRepo movieRepo) {
    this.movieRepo = movieRepo;
  }

  public String getStatement(Customer customer) {
    logger.info("Rental statement");

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      if (MovieType.REGULAR.equals(movieRepo.getMovieById(r.getMovieId()).getType())) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (MovieType.NEW.equals(movieRepo.getMovieById(r.getMovieId()).getType())) {
        thisAmount = r.getDays() * 3;
      }
      if (MovieType.CHILDREN.equals(movieRepo.getMovieById(r.getMovieId()).getType())) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (MovieType.NEW.equals(movieRepo.getMovieById(r.getMovieId()).getType()) && r.getDays() > 2) {
        frequentEnterPoints++;
      }

      //print figures for this rental
      result += "\t" + movieRepo.getMovieById(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
