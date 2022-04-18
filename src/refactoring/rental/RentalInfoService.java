package refactoring.rental;

import refactoring.movie.ChildrensMovie;
import refactoring.movie.Movie;
import refactoring.movie.NewMovie;
import refactoring.movie.RegularMovie;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RentalInfoService {

  private final Map<String, Movie> availableMovies;

  public static RentalInfoService withStandardMovies() {
    return new RentalInfoService(Map.of(
            "F001", new RegularMovie("You've Got Mail"),
            "F002", new RegularMovie("Matrix"),
            "F003", new ChildrensMovie("Cars"),
            "F004", new NewMovie("Fast & Furious X")));
  }

  public static RentalInfoService withMovies(Map<String, Movie> movies) {
    return new RentalInfoService(movies);
  }

  private RentalInfoService(Map<String, Movie> movies) {
    availableMovies = movies;
  }

  public String getCustomerStatus(Customer customer) {
    List<MovieRental> rentals = customer.getRentals();
    return "Rental Record for " +
            customer.getName() +
            "\n" +
            getInfoAboutAllRentals(rentals) +
            "Amount owed is " +
            getTotalAmount(rentals) +
            "\nYou earned " +
            getFrequentEnterPoints(rentals) +
            " frequent points\n";
  }

  private String getInfoAboutAllRentals(List<MovieRental> rentals) {
    return rentals.stream()
            .filter(this::movieExists)
            .map(this::getRentalAmountInfo)
            .collect(Collectors.joining());
  }

  private boolean movieExists(MovieRental rental) {
    return getMovie(rental) != null;
  }

  private Movie getMovie(MovieRental rental) {
    return availableMovies.get(rental.getMovieId());
  }

  private String getRentalAmountInfo(MovieRental rental) {
    return "\t" + getMovie(rental).getTitle() + "\t" +
            getMovie(rental).getRentalAmount(rental.getDays()) + "\n";
  }

  private double getTotalAmount(List<MovieRental> rentals) {
    return rentals.stream()
            .filter(this::movieExists)
            .mapToDouble(rental -> getMovie(rental).getRentalAmount(rental.getDays()))
            .sum();
  }

  private int getFrequentEnterPoints(List<MovieRental> rentals) {
    return rentals.stream()
            .filter(this::movieExists)
            .mapToInt(rental -> getMovie(rental).getFrequencyPoints(rental.getDays()))
            .sum();
  }
}
