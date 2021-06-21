package service;

import model.Customer;
import model.Movie;
import model.MovieRental;
import repository.impl.InMemoryMapMovieRepository;

import java.util.Map;

public class RentalInfo {

  public String statement(Customer customer) {
    Map<String, Movie> movies = InMemoryMapMovieRepository.movies;
    System.out.println(movies);

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      if (movies.get(r.getMovieId()).getRentalCategory().toString().equals("REGULAR")) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (movies.get(r.getMovieId()).getRentalCategory().toString().equals("NEW")) {
        thisAmount = r.getDays() * 3;
      }
      if (movies.get(r.getMovieId()).getRentalCategory().toString().equals("CHILDRENS")) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(r.getMovieId()).getRentalCategory().toString() == "new" && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
