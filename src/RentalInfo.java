

import java.util.HashMap;

public class RentalInfo {

    public String statement(Customer customer) {
        MovieLibrary movies = new MovieLibrary();
        String finalResult = "";
        try {
            // Creating customer record
            finalResult = "Rental Record for " + customer.getName() + "\n";
            finalResult += calculateTotalRent(movies.getMoviesFromLibrary(), customer);
        } catch (NullPointerException e) {
            finalResult = "Customer name is missing!";
        }
        return finalResult;
    }

    public String calculateTotalRent(HashMap<String, Movie> movies, Customer customer) {
        String result = "";
        double totalRent = 0;
        int frequentCheckInBonus = 0;

        try {
            for (MovieRental rentedMovie : customer.getMovies()) {
                double rent = 0;
                MovieType movieType = movies.get(rentedMovie.getMovieId()).getMovieType();
                // Calculate Rent for each movie depending on movie type
                if (movieType == MovieType.REGULAR) {
                    rent = movieType.getTotalRent(rentedMovie);
                } else if (movieType == MovieType.NEW) {
                    rent = movieType.getTotalRent(rentedMovie);
                    if (rentedMovie.getRentedDays() > 2) {
                        // Extra check-in point for movie type "NEW"
                        frequentCheckInBonus++;
                    }
                } else if (movieType == MovieType.CHILDRENS) {
                    rent = movieType.getTotalRent(rentedMovie);
                } else {
                    System.out.println("Movie Type not found!");
                }

                //add frequent bonus points
                frequentCheckInBonus++;

                //print figures for this each rental movie
                result += "\t" + movies.get(rentedMovie.getMovieId()).getMovieTitle() + "\t" + rent + "\n";
                totalRent = totalRent + rent;
            }
            // Calculating total rent and check-in points
            result += "Amount owed is " + totalRent + "\n";
            result += "You earned " + frequentCheckInBonus + " frequent points\n";
        } catch (Exception NullPointerException) {
            result = "Movie Type not found!";
        }
        return result;
    }
}
