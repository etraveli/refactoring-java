package service;

import models.Customer;
import models.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class RentalInfo {
    private final Map<String, Movie> movies;
    private static final String REGULAR = "regular";
    private static final String NEW = "new";
    private static final String CHILDREN = "children";

    public RentalInfo() {
        movies = new HashMap<>();
        getMovies();
    }

    private void getMovies() {
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));
    }

    public String statement(Customer customer) {
        AtomicReference<Double> totalAmount = new AtomicReference<>((double) 0);
        AtomicInteger loyaltyPoints = new AtomicInteger();
        StringBuilder result = new StringBuilder("Rental Record for ").append(customer.getName()).append("\n");
        customer.getRentals().forEach(movieRental -> {
            if (movies.containsKey(movieRental.getMovieId()) && movieRental.getDays() > 0) {
                Movie movie = movies.get(movieRental.getMovieId());
                double amountForThisMovie = calculateAmount(movie.getCategory(), movieRental.getDays());
                loyaltyPoints.getAndIncrement();
                if (movie.getCategory().equals("new") && movieRental.getDays() > 2) {
                    loyaltyPoints.getAndIncrement();
                }
                result.append("\t").append(movie.getTitle()).append("\t").append(amountForThisMovie).append("\n");
                totalAmount.updateAndGet(v -> v + amountForThisMovie);
            } else
                throw new RuntimeException("Movie with id: " + movieRental.getMovieId() + " is not present in the Database or the number of days the movie is to be rented should be more than 0");
        });
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(loyaltyPoints).append(" frequent points\n");
        return result.toString();
    }

    private double calculateAmount(String movieCategory, int rentalDays) {
        double amount = 0;
        switch (movieCategory) {
            case REGULAR:
                amount = 2;
                if (rentalDays > 2) {
                    amount += (rentalDays - 2) * 1.5;
                }
                break;
            case NEW:
                amount = rentalDays * 3;
                break;
            case CHILDREN:
                amount = 1.5;
                if (rentalDays > 3) {
                    amount += (rentalDays - 3) * 1.5;
                }
                break;
        }
        return amount;
    }
}
