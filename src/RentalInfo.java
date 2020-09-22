import java.util.List;

public class RentalInfo {
	final static String ERROR_MESSAGE = "Error";
	final static double AMOUNT_REGULAR = 2.0;
	final static double AMOUNT_CHILDREN = 1.5;
	
	// We made this basically a util class
	// Method is broken down into smaller unit method.
	// Very easy to read
	
	public static String statement(Customer customer) {
		if (customer == null || customer.getRentals() == null) return ERROR_MESSAGE;
		String result = "Rental Record for " + customer.getName() +  "\n";
		
		for(MovieRental rental : customer.getRentals()) {
			if (MovieDataStorage.getMovieById(rental.getMovieId()) == null) continue;
			result += "\t" + statement(rental);
		}
		
		// add footer lines
		result += "Amount owed is " + calculateAmount(customer.getRentals()) + "\n";
		result += "You earned " + calculateFrequentEnterPoint(customer.getRentals()) + " frequent points\n";  
		return result;
	}
	
	private static double calculateAmount(MovieRental rental) {
		Movie rentedMovie = MovieDataStorage.getMovieById(rental.getMovieId());
		if (MovieDataStorage.getMovieById(rental.getMovieId()) == null) return 0;
		
		switch(rentedMovie.getCode()) {
	    	case REGULAR:
	    		return (rental.getDays() > 2)? ((rental.getDays() - 2) * 1.5) + AMOUNT_REGULAR : AMOUNT_REGULAR;
	          
	    	case CHILDREN:
	    		return (rental.getDays() > 3)? ((rental.getDays() - 3) * 1.5) + AMOUNT_CHILDREN : AMOUNT_CHILDREN;
	           
	    	case NEW:
	    		return rental.getDays() * 3;
	    		
	    	default: 
	    		return 0;   		
		}
	}
	
	private static int calculateFrequentEnterPoint(MovieRental rental) {
		if (MovieDataStorage.getMovieById(rental.getMovieId()) == null) return 0;
		switch(MovieDataStorage.getMovieById(rental.getMovieId()).getCode()) {
			case NEW:
				// extra bonus for more than two days new release rental
				return (rental.getDays() > 2)? 2 : 1;
				
			default:
				return 1;			
		}	
	}
	
	private static String statement(MovieRental rental) {
		return MovieDataStorage.getMovieById(rental.getMovieId()).getTitle() + "\t" + calculateAmount(rental) + "\n";	
	}
	
	private static double calculateAmount(List<MovieRental> rentals) {
		return rentals.stream().map(x -> calculateAmount(x)).mapToDouble(Double::doubleValue).sum();
	}
	
	private static int calculateFrequentEnterPoint(List<MovieRental> rentals) {
		return rentals.stream().map(x -> calculateFrequentEnterPoint(x)).mapToInt(Integer::intValue).sum();
	}
}
