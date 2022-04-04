package validator;

import java.util.Objects;
import lombok.experimental.UtilityClass;
import model.Customer;
import model.Movie;
import model.MovieType;
import org.apache.log4j.Logger;

@UtilityClass
public class Validator {
  private static final Logger logger = Logger.getLogger(Validator.class);

  public static boolean isValidCustomer(Customer customer) {
    if (Objects.isNull(customer) || Objects.isNull(customer.getName())) {
      logger.warn("Invalid customer or name");
      throw new RuntimeException("Invalid Customer or customer name");
    }
    if (Objects.isNull(customer.getRentals())) {
      logger.warn("Invalid Movie Rentals or no movie Rentals");
      throw new RuntimeException("Invalid Movie Rentals or No movie Rentals");
    }
    return Boolean.TRUE;
  }

  public static boolean isValidMovie(Movie movie) {
    if (Objects.isNull(movie)) {
      return Boolean.FALSE;
    }
    if (Objects.isNull(MovieType.find(movie.getType()))) {
      throw new IllegalArgumentException("Invalid type in price calculation");
    }
    return Boolean.TRUE;
  }
}
