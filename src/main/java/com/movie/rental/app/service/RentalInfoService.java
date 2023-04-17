package com.movie.rental.app.service;

import com.movie.rental.app.exception.ResourceNotFoundException;
import com.movie.rental.app.model.Customer;
import com.movie.rental.app.model.Movie;
import com.movie.rental.app.model.MovieRental;
import com.movie.rental.app.enums.MovieCode;
import com.movie.rental.app.repository.DataProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This RentalInfoService class is used to generate information slip about movie rentals
 *
 * @author pabasara8857@gmail.com
 */
@Service
public class RentalInfoService {

  /**
   * Generate rental information statement
   *
   * @param customer Customer request object
   * @return generated rental information statement
   */
  public String generateRentalInfoStatement(Customer customer) {

    double totalAmount = 0;
    int frequentEnterPoints = 0;

    // Get movie data as a map
    Map<String, Movie> movies = getMovieMap();

    // Generate header info
    StringBuilder result = new StringBuilder(generateHeaderInfo(customer.getName()));

    for (MovieRental movieRental : customer.getRentals()) {
      Movie movie = movies.get(movieRental.getMovieId());
      if(movie == null) throw new ResourceNotFoundException("Cannot find movie with id : " + movieRental.getMovieId());

      // Calculate amount for a specific movie
      double amount = calculateAmountPerMovie(movie.getCode(), movieRental);

      // Calculate bonus points
      frequentEnterPoints = calculateBonusPointsPerMovie(frequentEnterPoints, movie.getCode(), movieRental.getDays());

      // Generate figures for this rental
      result.append(generateRentalInfo(movie.getTitle(), amount));

      // Calculate total amount
      totalAmount += amount;
    }

    // Generate footer info
    result.append(generateFooterInfo(totalAmount, frequentEnterPoints));

    return result.toString();
  }

  /**
   * Calculate amount for a specific movie
   *
   * @param movieCode movie code
   * @param movieRental MovieRental object
   * @return calculated amount for the specific movie
   */
  private double calculateAmountPerMovie(MovieCode movieCode, MovieRental movieRental){
    return switch (movieCode){
      case REGULAR -> movieRental.getDays() > 2 ? ((movieRental.getDays() - 2) * 1.5) + 2 : 2;
      case NEW -> movieRental.getDays() * 3;
      case CHILDREN -> movieRental.getDays() > 3 ? ((movieRental.getDays() - 3) * 1.5) + 1.5 : 1.5;
    };
  }

  /**
   * Calculate bonus points for a specific movie
   *
   * @param frequentEnterPoints frequent bonus points
   * @param movieCode movie code
   * @param days no of days
   * @return calculated bonus points for the specific movie
   */
  private int calculateBonusPointsPerMovie(int frequentEnterPoints, MovieCode movieCode, int days){
    // Add frequent bonus points
    frequentEnterPoints++;

    // Add bonus for a two days new release rental
    return movieCode.equals(MovieCode.NEW) && days > 2 ? ++frequentEnterPoints : frequentEnterPoints;
  }

  /**
   * Generate header information
   *
   * @param customerName name of the customer
   * @return generated header information
   */
  private String generateHeaderInfo(String customerName){
    return "Rental Record for " + customerName + "\n";
  }

  /**
   * Generate rental information
   *
   * @param title movie title
   * @param amount amount for a specific movie
   * @return generated rental information
   */
  private String generateRentalInfo(String title, double amount){
    return "\t" + title + "\t" + amount + "\n";
  }

  /**
   * Generate footer information
   *
   * @param totalAmount total amount owed
   * @param frequentEnterPoints frequent points earned
   * @return generated footer information
   */
  private String generateFooterInfo(double totalAmount, int frequentEnterPoints){
    return "Amount owed is " + totalAmount + "\n" +
    "You earned " + frequentEnterPoints + " frequent points\n";
  }

  /**
   * Fetch all movies and generate a map
   * Note: this movie map can be cached to enhance performance
   *
   * @return all movies in a map
   */
  public static Map<String, Movie> getMovieMap() {
    List<Movie> movies = DataProvider.getMovies();
    return movies.stream().collect(Collectors.toMap(Movie::getMovieId, Function.identity()));
  }
}
