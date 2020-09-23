
public class RentalInfo {

	public String statement(Customer customer) {
		if(customer == null) {
			return "No Customer Error";
		}
		
		double totalRentalAmount = 0;
		int frequentEnterPoints = 0;

		// using StringBuilder instead of String
		StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

		// In case if Customer has no Rentals
		if (customer.getRentals() == null) {
			result.append("\t" + "No Movie List" + "\n");
			return result.toString();
		}

		for (MovieRental rentals : customer.getRentals()) {
			double movieRentalAmount = 0;

			Movie movie = Movie.getMovieById(rentals.getMovieId());

			if (movie != null) {

				if (rentals.getDays() < 1) {
					result.append("\t" + movie.getTitle() + "\t" + rentals.getDays() + "(Invalid Days)\n");
					continue;
				}

				// Get Movie Rental from Movie Class
				movieRentalAmount = movie.getRental(rentals.getDays());

				// add frequent bonus points
				frequentEnterPoints++;
				
				// add bonus for a two day new release rental
				if(movie.isBonusEligible(rentals.getDays())) {
					frequentEnterPoints++;
				}

				// print figures for this rental
				result.append("\t" + movie.getTitle() + "\t" + movieRentalAmount + "\n");
				totalRentalAmount = totalRentalAmount + movieRentalAmount;
				
			} else {
				result.append("\t" + rentals.getMovieId() + "(Invalid Id)" + "\t" + movieRentalAmount + "\n");
			}

		}
		// add footer lines
		result.append("Amount owed is " + totalRentalAmount + "\n");
		result.append("You earned " + frequentEnterPoints + " frequent points\n");

		return result.toString();
	}
}
