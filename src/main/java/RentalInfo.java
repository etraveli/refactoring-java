import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalInfo {

  public Statement getStatement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));

    double totalAmount = 0;
    int frequentEnterPoints = 0;

    Statement resultStatement = new Statement(customer.getName());
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      Movie m = movies.get(r.getMovieId());
      if (m == null) { continue; }
      if (m.getCode().equals("regular")) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (m.getCode().equals("new")) {
        thisAmount = r.getDays() * 3;
      }
      if (m.getCode().equals("childrens")) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (m.getCode() == "new" && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      resultStatement.addRental(m.getTitle(), thisAmount);
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    resultStatement.addFooter(totalAmount, frequentEnterPoints);

    return resultStatement;
  }
}

