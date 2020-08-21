import java.util.HashMap;

public class RentalInfo {

	public String statement(Customer customer, HashMap<String, Movie> movies) {
    	double totalAmount = 0;
		int frequentEnterPoints = 0;
		String result = "Rental Record for " + customer.getName() + "\n";
		//Changing variable to meaningful name
		for (MovieRental rental : customer.getRentals()) {
			double thisAmount = 0;

			// determine amount for each movie
			//Making Variables for calculation, readability and getting value one time in a loop
			String currentRentalMovieCode = movies.get(rental.getMovieId()).getCode();
			String currentRentalMovieTitle = movies.get(rental.getMovieId()).getTitle();
			/*
			 * Converted to switch case as "currentRentalMovieCode" is simple string and it
			 * will be faster if types of codes increase in future, rather than if or
			 * if-else and also each rental can have only one type of code
			 */
			switch (currentRentalMovieCode) {
				case "regular" :
					thisAmount = 2;
					if (rental.getDays() > 2) {
						thisAmount = ((rental.getDays() - 2) * 1.5) + thisAmount;
					}
					break;
				case "new" :
					thisAmount = rental.getDays() * 3;
					break;
				case "childrens" :
					thisAmount = 1.5;
					if (rental.getDays() > 3) {
						thisAmount = ((rental.getDays() - 3) * 1.5) + thisAmount;
					}
					break;
				
			}

			//add frequent bonus points
			frequentEnterPoints++;
			// add bonus for a two day new release rental
			if (currentRentalMovieCode == "new" && rental.getDays() > 2) frequentEnterPoints++;

			//print figures for this rental
			result += "\t" + currentRentalMovieTitle + "\t" + thisAmount + "\n";
			totalAmount = totalAmount + thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentEnterPoints + " frequent points\n";

		return result;
	}
}
