import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RentalInfoService {

  private static final Map<String, Movie> availableMovies = Map.of(
          "F001", new RegularMovie("You've Got Mail"),
          "F002", new RegularMovie("Matrix"),
          "F003", new ChildrensMovie("Cars"),
          "F004", new NewMovie("Fast & Furious X"));

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
            .filter(RentalInfoService::movieExists)
            .map(RentalInfoService::getRentalAmountInfo)
            .collect(Collectors.joining());
  }

  private static boolean movieExists(MovieRental rental) {
    return getMovie(rental) != null;
  }

  private static Movie getMovie(MovieRental rental) {
    return availableMovies.get(rental.getMovieId());
  }

  private static String getRentalAmountInfo(MovieRental rental) {
    return "\t" + getMovie(rental).getTitle() + "\t" +
            getMovie(rental).getRentalAmount(rental.getDays()) + "\n";
  }

  private double getTotalAmount(List<MovieRental> rentals) {
    return rentals.stream()
            .filter(RentalInfoService::movieExists)
            .mapToDouble(rental -> getMovie(rental).getRentalAmount(rental.getDays()))
            .sum();
  }

  private int getFrequentEnterPoints(List<MovieRental> rentals) {
    return rentals.stream()
            .filter(RentalInfoService::movieExists)
            .mapToInt(rental -> getMovie(rental).getFrequencyPoints(rental.getDays()))
            .sum();
  }
}
