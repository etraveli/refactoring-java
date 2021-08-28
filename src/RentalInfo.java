

import java.util.HashMap;

public class RentalInfo {

    public String statement(Customer customer) {
        MovieLibrary movies = new MovieLibrary();
        String finalResult = "";
        try {
            finalResult = "Rental Record for " + customer.getCustomerName() + "\n";
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
            for (MovieRental rentedMovie : customer.getRentedMovies()) {
                double rent = 0;
                MovieType movieType = movies.get(rentedMovie.getRentedMovieId()).getMovieType();
                // Calculate Rent for each movie
                if (movieType == MovieType.REGULAR) {
                    rent = movieType.getTotalRent(rentedMovie);
                } else if (movieType == MovieType.NEW) {
                    rent = movieType.getTotalRent(rentedMovie);
                    if (rentedMovie.getMovieRentedDays() > 2) {
                        // add bonus for a two day new release rental
                        frequentCheckInBonus++;
                    }
                } else if (movieType == MovieType.CHILDRENS) {
                    rent = movieType.getTotalRent(rentedMovie);
                } else {
                    System.out.println("Movie Type not found!");
                }

                //add frequent bonus points
                frequentCheckInBonus++;

                //print figures for this rental
                result += "\t" + movies.get(rentedMovie.getRentedMovieId()).getMovieTitle() + "\t" + rent + "\n";
                totalRent = totalRent + rent;
            }
            // add footer lines
            result += "Amount owed is " + totalRent + "\n";
            result += "You earned " + frequentCheckInBonus + " frequent points\n";


        } catch (Exception NullPointerException) {
            result = "Movie Type not found!";
        }

        return result;
    }

}
