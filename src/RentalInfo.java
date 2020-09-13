import java.util.HashMap;
import java.util.Map;

public class RentalInfo {

  /**
   * Make it static because it is just for calculation, no instance data. So, no
   * need to create object of RentalInfo
   */
  public static Statement generateStatement(Customer customer) {
    // Handel some corner cases
    if (customer == null || customer.getRentals().isEmpty()) {
      return new Statement();
    }

    DataAccess dataAccess = new DataAccess();
    Map<String, Movie> movies = dataAccess.getAllMovies();

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    Map<String, Double> moviesAmounts = new HashMap<String, Double>();
    // Declare the reference outside the loop
    String currMovieCode;

    for (MovieRental rental : customer.getRentals()) {

      double currAmount = 0;
      currMovieCode = movies.get(rental.getMovieId()).getCode();

      // determine amount for each movie

      /**
       * Using 'switch' is not just more readable but also it is better in performance
       * than separated if conditions and if-else chain
       */
      switch (currMovieCode) {
        case Const.Code.REGULAR:
          currAmount = 2;
          if (rental.getDays() > 2) {
            currAmount = currAmount + ((rental.getDays() - 2) * 1.5);
          }
          break;
        case Const.Code.NEW:
          currAmount = rental.getDays() * 3;
          // add bonus for a two day new release rental
          if (rental.getDays() > 2) {
            frequentEnterPoints++;
          }
          break;
        case Const.Code.CHILDREN:
          currAmount = 1.5;
          if (rental.getDays() > 3) {
            currAmount = currAmount + ((rental.getDays() - 3) * 1.5);
          }
      }

      // add frequent bonus points
      frequentEnterPoints++;

      moviesAmounts.put(movies.get(rental.getMovieId()).getTitle(), currAmount);
      totalAmount = totalAmount + currAmount;
    }

    Statement statement = new Statement();
    statement.setCustomerName(customer.getName());
    statement.setTotalAmount(totalAmount);
    statement.setFrequentEnterPoints(frequentEnterPoints);
    statement.setMoviesAmounts(moviesAmounts);

    return statement;
  }
}
