package service;

import entity.Customer;
import entity.Movie;
import entity.MovieRental;
import entity.RentalResult;
import helper.MovieHelper;

import java.util.HashMap;

public class RentalService {

    public String createRentalStatement(Customer customer) {

        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

        RentalResult rentalResult = generateRentalResult(customer);

        result.append(rentalResult.getResult());

        // add footer lines
        result.append("Amount owed is " + rentalResult.getTotalAmount() + "\n");
        result.append("You earned " + rentalResult.getFrequentEnterPoints() + " frequent points\n");

        return result.toString();
    }


    public RentalResult generateRentalResult(Customer customer) {

        StringBuilder result = new StringBuilder();
        double totalAmount = 0;
        int frequentEnterPoints = 0;
        HashMap<String, Movie> movies = MovieHelper.generateMoviesMap();

        //TODO please do all null checks
        for (MovieRental r : customer.getRentals()) {
            double thisAmount;

            switch (movies.get(r.getMovieId()).getMovieType()) {
                case REGULAR:
                    thisAmount = 2;
                    if (r.getDays() > 2) {
                        thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
                    }
                    break;

                case NEW:
                    thisAmount = r.getDays() * 3;
                    // add bonus for a two day new release rental
                    if (r.getDays() > 2) {
                        frequentEnterPoints++;
                    }
                    break;

                default: //TODO not sure if it should be children or default
                    thisAmount = 1.5;
                    if (r.getDays() > 3) {
                        thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
                    }
                    break;
            }

            //add frequent bonus points
            frequentEnterPoints++;

            //print figures for this rental
            result.append("\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n");
            totalAmount = totalAmount + thisAmount;
        }
        //TODO you can use builder pattern there
        RentalResult rentalResult = new RentalResult(result, totalAmount, frequentEnterPoints);
        return rentalResult;
    }
}
