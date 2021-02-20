package main;

import main.Customer;

import java.util.HashMap;



public class RentalInfo {


  // A static final cuz we want to intialize movies hashmap once and not change it.
  private static final HashMap<String, Movie> movies = new HashMap<String, Movie>() {{
    put("F001", new Movie("You've Got Mail", MovieCode.REGULAR));
    put("F002", new Movie("Matrix", MovieCode.REGULAR));
    put("F003", new Movie("Cars", MovieCode.CHILDRENS));
    put("F004", new Movie("Fast & Furious X", MovieCode.NEW));
  }};


  public String statement(Customer customer) {

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;
      // good to define a variable accessing movies once rather than on ever condition. Makes code more readable. If its a database call makes positive difference.
      MovieCode movieCode = movies.get(r.getMovieId()).getCode();
      // determine amount for each movie
      if (movieCode.equals(MovieCode.REGULAR)) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (movieCode.equals(MovieCode.NEW)) {
        thisAmount = r.getDays() * 3;
        // add bonus for a two day new release rental
        //moved here to avoid repetition of checking conditions
        if(r.getDays() > 2)
          frequentEnterPoints++;
      }
      if (movieCode.equals(MovieCode.CHILDRENS)) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }
      //add frequent bonus points
      frequentEnterPoints++;


      //print figures for this rental
      result.append("\t").append(movies.get(r.getMovieId()).getTitle()).append("\t").append(thisAmount).append("\n");
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }
}
