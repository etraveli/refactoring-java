package services;
import models.Customer;
import models.Movie;
import models.MovieRental;

public class RentalInfoService {
  private static MovieService movieService;
  private static MovieRentalService movieRentalService;

  public RentalInfoService() {
    movieService = new MovieService();
    movieRentalService = new MovieRentalService();
  }

  public String statement(Customer customer) {
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for ").append(customer.getName()).append( "\n");
    for (MovieRental movieRental : customer.getRentals()) {
      Movie movie = movieService.getMovieByMovieId(movieRental.getMovieId());
      movieRental.setMovie(movie);
      double thisAmount = movieRentalService.getAmountPerMovie(movieRental);

      //add frequent bonus points
      frequentEnterPoints++;
      if(movieRentalService.increaseFrequentEnterPoints(movieRental)){
        frequentEnterPoints++;
      }

      //print figures for this rental
      result.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append("\n");
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\nYou earned ").append(frequentEnterPoints).append(" frequent points\n");
    return result.toString();
  }
}
