package movie.rental;

import java.util.HashMap;
import customer.Customer;
import movie.Movie;
import movie.MovieRepo;
import movie.code.exceptions.MovieCodeInstantiationException;
import movie.code.exceptions.MovieCodeNotFoundException;

public class RentalInfo {

    public String statement(Customer customer) throws MovieCodeNotFoundException,
            MovieCodeInstantiationException {
        HashMap<String, Movie> movies = new MovieRepo().fetch().indexById();

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        String result = "Rental Record for " + customer.getName() + "\n";

        for (MovieRental r : customer.getRentals()) {
            Movie movie = movies.get(r.getMovieId());

            int days = r.getDays();
            double thisAmount = movie.calculateAmount(days);

            frequentEnterPoints++;
            if (movie.hasExtraBonusPoint(days))
                frequentEnterPoints++;

            // print figures for this rental
            result += "\t" + movie + "\t" + thisAmount + "\n";
            totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentEnterPoints + " frequent points\n";

        return result;
    }
}
