package com.etraveli.app.movie.rental.service;

import com.etraveli.app.movie.rental.model.*;

import com.etraveli.app.movie.rental.repository.MovieRepository;
import com.etraveli.app.movie.rental.util.CalculateUtils;
import com.etraveli.app.movie.rental.util.Constants;

/**
 * Generate Statements for Rental Information
 */
public class RentalInfoService {

    /**
     * Generates movie rentals statement for a customer
     * @param customer Customer object
     * @return String with generated rental information
     */
    public String statement(Customer customer) {
        MovieRepository movieRepo = new MovieRepository();
        Statement statement = new Statement(customer.getName());

        for (MovieRental rental : customer.getRentals()) {
            Movie movie = movieRepo.findMovieById(rental.getMovieId());
            if (movie == null)
                continue;
            MovieCategory movieCode = movie.getType();
            int days = rental.getDays();
            // determine amount for each movie
            double price = CalculateUtils.calculateRentalPrice(movieCode, days);
            //add frequent bonus points
            int points = CalculateUtils.calculateFrequentPoints(movieCode, days);
            statement.addMovieItem(new StatementMovieItem(movie.getTitle(), price, points));
        }
        return generateStatement(statement);
    }

    /**
     * Format movie rental statement with header, footer and contents
     * @param statement Statement object with customer name and movie rental info
     * @return String as a formatted statement
     */
    public String generateStatement(Statement statement) {
        StringBuilder sb = new StringBuilder();
        // add header line
        sb.append("Rental Record for ");
        sb.append(statement.getCustomerName());
        sb.append(System.lineSeparator());

        //print figures for a rental
        for(StatementMovieItem movieItem: statement.getMovieItems()) {
            sb.append(Constants.TAB_SEPARATOR);
            sb.append(movieItem.getMovieName());
            sb.append(Constants.TAB_SEPARATOR);
            sb.append(movieItem.getPrice());
            sb.append(System.lineSeparator());
        }
        // add footer lines
        sb.append("Amount owed is ");
        sb.append(statement.getTotalPrice());
        sb.append(System.lineSeparator());
        sb.append("You earned ");
        sb.append(statement.getFrequentPoints());
        sb.append(" frequent points");
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
