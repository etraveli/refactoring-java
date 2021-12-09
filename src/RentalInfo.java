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
  	        thisAmount = ApplicationConfiguration.FIXED_RATE_TILL_DEFAULT_DAYS_FOR_REGULAR_MOVIES;
  	        if (r.getDays() > ApplicationConfiguration.DEFAULT_DAYS_FOR_REGULAR_MOVIES) {
  	          thisAmount = ((r.getDays() - ApplicationConfiguration.DEFAULT_DAYS_FOR_REGULAR_MOVIES) 
  	        		  * ApplicationConfiguration.PER_DAY_RATE_AFTER_DEFAULT_DAYS_FOR_REGULAR_MOVIES) + thisAmount;
  	        }
  	      }
  	      
  			// here we can have same formula as above which will allow client to add same
  			// config as above from Application configuration class
  	      if (MovieCodeType.NEW.equals(movies.get(r.getMovieId()).getCode())) {
  	        thisAmount = r.getDays() * ApplicationConfiguration.FIXED_RATE_TILL_DEFAULT_DAYS_FOR_NEW_MOVIES;
  	      }
  	      if (MovieCodeType.CHILDRENS.equals(movies.get(r.getMovieId()).getCode())) {
  	        thisAmount = ApplicationConfiguration.FIXED_RATE_TILL_DEFAULT_DAYS_FOR_CHILDREN_MOVIES;
  	        if (r.getDays() > ApplicationConfiguration.DEFAULT_DAYS_FOR_CHILDREN_MOVIES) {
  	          thisAmount = ((r.getDays() - ApplicationConfiguration.DEFAULT_DAYS_FOR_CHILDREN_MOVIES) 
  	        		  * ApplicationConfiguration.PER_DAY_RATE_AFTER_DEFAULT_DAYS_FOR_CHILDREN_MOVIES) + thisAmount;
  	        }
  	      }
	
	      //add frequent bonus points
	      frequentEnterPoints++;
	      // add bonus for a two day new release rental
	      if (MovieCodeType.NEW.equals(movies.get(r.getMovieId()).getCode()) 
	    		  && r.getDays() > ApplicationConfiguration.DEFAULT_DAYS_FOR_NEW_MOVIES_TO_GET_BONUS_POINTS) frequentEnterPoints++;
	
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
