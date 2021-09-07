package run;

import entity.Customer;
import entity.Movie;
import entity.MovieRental;
import entity.RentalResult;
import org.apache.log4j.Logger;
import service.RentalService;
import java.util.Arrays;
import java.util.HashMap;



public class Main {

    public static final String F001_MOVIE_CODE = "F001";
    public static final String F002_MOVIE_CODE = "F002";

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        RentalResult rentalResult = new RentalService().createRentalStatement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        HashMap<String, Movie> movieHashMap = rentalResult.getMovieMap();

        if (!movieHashMap.get(F001_MOVIE_CODE).getTitle().equals("You've Got Mail")) {
            logger.error("Expected Movie Name: You've Got Mail Got: " + movieHashMap.get(F001_MOVIE_CODE).getTitle());
            throw new AssertionError("Expected Movie Name: You've Got Mail Got: " + movieHashMap.get(F001_MOVIE_CODE).getTitle());
        }

        if (movieHashMap.get(F001_MOVIE_CODE).getAmount() != 3.5) {
            logger.error("Expected Movie Amount: 3.5: " + movieHashMap.get(F001_MOVIE_CODE).getAmount());
            throw new AssertionError("Expected Movie Amount: 3.5: " + movieHashMap.get(F001_MOVIE_CODE).getAmount());
        }

        if (!movieHashMap.get(F002_MOVIE_CODE).getTitle().equals("Matrix")) {
            logger.error("Expected Movie Name: Matrix: " + movieHashMap.get(F002_MOVIE_CODE).getTitle());
            throw new AssertionError("Expected Movie Name: Matrix: " + movieHashMap.get(F002_MOVIE_CODE).getTitle());
        }

        if (movieHashMap.get(F002_MOVIE_CODE).getAmount() != 2.0) {
            logger.error("Expected Movie Amount: 2.0: " + movieHashMap.get(F002_MOVIE_CODE).getAmount());
            throw new AssertionError("Expected Movie Amount: 2.0: " + movieHashMap.get(F002_MOVIE_CODE).getAmount());
        }

        if (rentalResult.getTotalAmount() != 5.5) {
            logger.error("Expected Total Amount: 5.5: " + rentalResult.getTotalAmount());
            throw new AssertionError("Expected Total Amount: 5.5: " + rentalResult.getTotalAmount());
        }

        if (rentalResult.getFrequentEnterPoints() != 2) {
            logger.error("Expected Frequent Points: 2: " + rentalResult.getFrequentEnterPoints());
            throw new AssertionError("Expected Frequent Points: 2: " + rentalResult.getFrequentEnterPoints());
        }


        System.out.println("Success");
        logger.info("Success");
    }

}
