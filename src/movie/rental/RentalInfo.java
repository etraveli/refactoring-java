package movie.rental;

import java.util.HashMap;
import customer.Customer;
import movie.Movie;
import movie.code.exceptions.MovieCodeInstantiationException;
import movie.code.exceptions.MovieCodeNotFoundException;

public class RentalInfo {

  public String statement(Customer customer) throws MovieCodeNotFoundException,
      MovieCodeInstantiationException {
    HashMap<String, Movie> movies = new HashMap<>();
    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      int days = r.getDays();
      Movie movie = movies.get(r.getMovieId());

      double thisAmount = movie.calculateAmount(days);

      frequentEnterPoints++;
      if (movie.hasExtraBonusPoint(days)) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
