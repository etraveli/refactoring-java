import java.util.ArrayList;
import java.util.List;

public class RentalInfo {
	
  final static String ERROR_MESSAGE = "Error";
  
  // We make this method static as we don't need to create any object of this class.
  public static String statement(Customer customer) {
	// Check some null points 
	if (customer == null) return ERROR_MESSAGE;
	if (customer.getRentals() == null) return ERROR_MESSAGE;

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      Movie rentedMovie = MovieDataStorage.getMovieById(r.getMovieId());
      if (rentedMovie == null) continue;
      
      double thisAmount = 0;

      // determine amount for each movie   
      
      // A series of nested if else is replaces with switch statement. It is faster and improves readability.
      switch(rentedMovie.getCode()) {
    	case REGULAR:
    		thisAmount = 2;
          if (r.getDays() > 2) {
            thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
          }
          break;
          
    	case CHILDREN:
    		 thisAmount = 1.5;
           if (r.getDays() > 3) {
             thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
           }
           break;
           
    	case NEW:
    		thisAmount = r.getDays() * 3;
    		// add bonus for more than two days new release rental
    		if (r.getDays() > 2) {
    			frequentEnterPoints++;
            }
      }

      // add frequent bonus points
      frequentEnterPoints++;     

      // print figures for this rental
      result += "\t" + rentedMovie.getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
