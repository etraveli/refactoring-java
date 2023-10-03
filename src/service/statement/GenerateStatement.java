package service.statement;

import pojo.Customer;
import pojo.Movie;
import pojo.MovieRental;
import service.price.ChildrenMovies;
import service.price.NewMovies;
import service.price.RegularMovies;
import utils.Constants;
import utils.MoviesDB;

import java.util.Map;

public class GenerateStatement {

    Map<String, Movie> movies = new MoviesDB().getMovies();
    Constants objConstants = new Constants();
    private int points = 0;
    private double totalAmt = 0;

    public String statement (Customer customer) {

        StringBuilder statement = new StringBuilder();
        statement.append("Rental Record for ").append(customer.getName()).append("\n");

        for(MovieRental mr : customer.getRentals()){

            Movie movie = movies.get(mr.getMovieId());

            if(movie != null) {
                double rate = calculateRate(movie.getCode(), mr.getDays());
                statement.append(String.format("\t%s\t%.1f%n", movie.getTitle(), rate));
                totalAmt += rate;
                points ++;
            }
        }

        statement.append(String.format("Amount owed is %.1f%n", totalAmt));
        statement.append("You earned ").append(points).append(" frequent points\n");

        return statement.toString();
    }

    private double calculateRate (String type, int days) {
        double amt = 0;
        if(type.equalsIgnoreCase(objConstants.REGULAR)) {
            amt = new RegularMovies().calculate(days);
        } else if(type.equalsIgnoreCase(objConstants.CHILDRENS)){
            amt = new ChildrenMovies().calculate(days);
        } else if(type.equalsIgnoreCase(objConstants.NEW)){
            amt = new NewMovies().calculate(days);
            if(days > 2)
                points ++;
        }
        return amt;
    }

}
