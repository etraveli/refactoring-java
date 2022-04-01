package service;

import dao.MovieRepo;
import java.util.Objects;
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

  /**
   *
   * @param customer
   * @return The Rental statement for all the movies rented by the customer
   */
  public String getStatement(Customer customer) {
    logger.info("Getting Rental statement");
    //Could be replaced to a separate validator class with custom exceptions
    if (Objects.isNull(customer) || Objects.isNull(customer.getName())) {
      logger.warn("Invalid customer");
      return "Invalid Customer or customer name";
    }
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;
      //Get movie from movie Repo
      var movie = movieRepo.getMovieById(r.getMovieId());
      if (Objects.nonNull(movie)) {
        // determine amount for each movie
        if (MovieType.REGULAR.equals(movie.getType())) {
          thisAmount = 2;
          if (r.getDays() > 2) {
            thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
          }
        }
        if (MovieType.NEW.equals(movie.getType())) {
          thisAmount = r.getDays() * 3;
        }
        if (MovieType.CHILDREN.equals(movie.getType())) {
          thisAmount = 1.5;
          if (r.getDays() > 3) {
            thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
          }
        }

        //add frequent bonus points
        frequentEnterPoints++;
        // add bonus for a two day new release rental
        if (MovieType.NEW.equals(movie.getType()) && r.getDays() > 2) {
          frequentEnterPoints++;
        }

        //print figures for this rental
        result.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append("\n");
        totalAmount = totalAmount + thisAmount;
      } else {
        logger.warn("No movies found for id: " + r.getMovieId());
        result.append("\t" + "No movies found for id: ").append(r.getMovieId()).append("\n");
      }
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }
}
