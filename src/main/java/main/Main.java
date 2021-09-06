package main;

import entity.Customer;
import entity.Movie;
import entity.MovieRental;
import entity.RentalResult;
import service.RentalService;

import java.util.Arrays;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {

        RentalResult rentalResult = new RentalService().createRentalStatement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        HashMap<String, Movie> movieHashMap = rentalResult.getMovieMap();

        if (!movieHashMap.get("F001").getTitle().equals("You've Got Mail")) {
            throw new AssertionError("Expected Movie Name: You've Got Mail Got: " + movieHashMap.get("F001").getTitle());
        }

        if (movieHashMap.get("F001").getAmount() != 3.5) {
            throw new AssertionError("Expected Movie Amount: 3.5: " + movieHashMap.get("F001").getAmount());
        }

        if (!movieHashMap.get("F002").getTitle().equals("Matrix")) {
            throw new AssertionError("Expected Movie Name: Matrix: " + movieHashMap.get("F002").getTitle());
        }

        if (movieHashMap.get("F002").getAmount() != 2.0) {
            throw new AssertionError("Expected Movie Amount: 2.0: " + movieHashMap.get("F002").getAmount());
        }

        if (rentalResult.getTotalAmount() != 5.5) {
            throw new AssertionError("Expected Total Amount: 5.5: " + rentalResult.getTotalAmount());
        }

        if (rentalResult.getFrequentEnterPoints() != 2) {
            throw new AssertionError("Expected Frequent Points: 2: " + rentalResult.getFrequentEnterPoints());
        }


        System.out.println("Success");
    }

}
