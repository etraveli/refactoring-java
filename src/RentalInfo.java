import java.util.HashMap;

public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap<>();
    movies.put("F001", new Movie("You've Got Mail", MovieCodeType.REGULAR));
    movies.put("F002", new Movie("Matrix", MovieCodeType.REGULAR));
    movies.put("F003", new Movie("Cars", MovieCodeType.CHILDRENS));
    movies.put("F004", new Movie("Fast & Furious X", MovieCodeType.NEW));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      if (movies.get(r.getMovieId()) != null) {
	      if (MovieCodeType.REGULAR.equals(movies.get(r.getMovieId()).getCode())) {
	        thisAmount = 2;
	        if (r.getDays() > 2) {
	          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
	        }
	      }
	      if (MovieCodeType.NEW.equals(movies.get(r.getMovieId()).getCode())) {
	        thisAmount = r.getDays() * 3;
	      }
	      if (MovieCodeType.CHILDRENS.equals(movies.get(r.getMovieId()).getCode())) {
	        thisAmount = 1.5;
	        if (r.getDays() > 3) {
	          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
	        }
	      }
	
	      //add frequent bonus points
	      frequentEnterPoints++;
	      // add bonus for a two day new release rental
	      if (MovieCodeType.NEW.equals(movies.get(r.getMovieId()).getCode()) && r.getDays() > 2) frequentEnterPoints++;
	
	      //print figures for this rental
	      result.append("\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n");
	      totalAmount = totalAmount + thisAmount;
	    }
    }
    // add footer lines
    result.append("Amount owed is " + totalAmount + "\n");
    result.append("You earned " + frequentEnterPoints + " frequent points\n");

    return result.toString();
  }
}
