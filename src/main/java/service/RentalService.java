package service;

import entity.Customer;
import entity.Movie;
import entity.MovieRental;
import entity.RentalResult;
import helper.MovieHelper;

import java.util.HashMap;

public class RentalService {

    public RentalResult createRentalStatement(Customer customer) {

        RentalResult rentalResult = generateRentalResult(customer);

        return rentalResult;
    }


    public RentalResult generateRentalResult(Customer customer) {

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        HashMap<String, Movie> movieMap = MovieHelper.generateMoviesMap();

        //TODO please do all null checks
        for (MovieRental r : customer.getRentals()) {
            double thisAmount;

            switch (movieMap.get(r.getMovieId()).getMovieType()) {
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
            movieMap.get(r.getMovieId()).setAmount(thisAmount);
            //print figures for this rental
            //TODO liste icerisine at ! Listeyi sonra kontrol et !!!
            movieMap.get(r.getMovieId()).setAmount(thisAmount);
            totalAmount = totalAmount + thisAmount;
        }
        //TODO you can use builder pattern there
        RentalResult rentalResult = new RentalResult(totalAmount, frequentEnterPoints, movieMap);
        return rentalResult;
    }
}
