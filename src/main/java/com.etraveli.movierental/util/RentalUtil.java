package com.etraveli.movierental.util;

import com.etraveli.movierental.Movie;
import com.etraveli.movierental.Customer;
import com.etraveli.movierental.MovieRental;
import com.etraveli.movierental.enumeration.CodeType;

import java.util.HashMap;

public class RentalUtil {

  private RentalUtil() {
  }

  private static HashMap<String, Movie> movies = new HashMap<>();

  static {
    movies.put("F001", new Movie("You've Got Mail", CodeType.REGULAR));
    movies.put("F002", new Movie("Matrix", CodeType.REGULAR));
    movies.put("F003", new Movie("Cars", CodeType.CHILDREN));
    movies.put("F004", new Movie("Fast & Furious X", CodeType.NEW));
  }

  public static String createStatement(Customer customer) {
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder();
    appendStrings(result, "Rental Record for ", customer.getName(), "\n");

    for (MovieRental rental : customer.getRentals()) {
        CodeType codeType = movies.get(rental.getMovieId()).getCode();
        double thisAmount = calculateAmount(codeType, rental);

        //add frequent bonus points
        frequentEnterPoints++;
        // add bonus for a two day new release rental
        if (codeType == CodeType.NEW && rental.getDays() > 2) frequentEnterPoints++;

        appendStrings(result, "\t", movies.get(rental.getMovieId()).getTitle(), "\t", thisAmount, "\n");
        totalAmount = totalAmount + thisAmount;
    }

    appendStrings(result, "Amount owed is ", totalAmount, "\nYou earned ", frequentEnterPoints, " frequent points\n");


    return result.toString();
  }

    private static double calculateAmount(CodeType codeType, MovieRental rental) {
        double currentAmount;
        switch (codeType) {
            case REGULAR:
                currentAmount = 2;
                if (rental.getDays() > 2) {
                    currentAmount = ((rental.getDays() - 2) * 1.5) + currentAmount;
                }
                return currentAmount;
            case NEW:
                return rental.getDays() * 3;
            case CHILDREN:
                currentAmount = 1.5;
                if (rental.getDays() > 3) {
                    currentAmount = ((rental.getDays() - 3) * 1.5) + currentAmount;
                }
                return currentAmount;
            default:
                throw new RuntimeException("Given codeType: " + codeType + " does not exist");
        }
    }

    private static void appendStrings(StringBuilder result, Object... strings) {
        for(Object appendString: strings) {
            result.append(appendString);
        }
    }
}
