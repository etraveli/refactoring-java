import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RentalInfoService {

  private static final Map<String, Movie> availableMovies = Map.of(
          "F001", new Movie("You've Got Mail", "regular"),
          "F002", new Movie("Matrix", "regular"),
          "F003", new Movie("Cars", "childrens"),
          "F004", new Movie("Fast & Furious X", "new"));

  public String getCustomerStatus(Customer customer) {
    List<MovieRental> rentals = customer.getRentals() == null
            ? List.of()
            : customer.getRentals();

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
            calculateCurrentRentalAmount(getMovie(rental), rental) + "\n";
  }

  private static double calculateCurrentRentalAmount(Movie movie, MovieRental rental) {
    double currentRentalAmount = 0;
    if (movie.getCode().equals("regular")) {
      currentRentalAmount = 2;
      if (rental.getDays() > 2) {
        currentRentalAmount = ((rental.getDays() - 2) * 1.5) + currentRentalAmount;
      }
    } else if (movie.getCode().equals("new")) {
      currentRentalAmount = rental.getDays() * 3;
    } else if (movie.getCode().equals("childrens")) {
      currentRentalAmount = 1.5;
      if (rental.getDays() > 3) {
        currentRentalAmount = ((rental.getDays() - 3) * 1.5) + currentRentalAmount;
      }
    }
    return currentRentalAmount;
  }

  private double getTotalAmount(List<MovieRental> rentals) {
    return rentals.stream()
            .filter(RentalInfoService::movieExists)
            .mapToDouble(rental -> calculateCurrentRentalAmount(getMovie(rental), rental))
            .sum();
  }

  private int getFrequentEnterPoints(List<MovieRental> rentals) {
    return rentals.stream()
            .filter(RentalInfoService::movieExists)
            .mapToInt(rental -> calculateFrequentPoints(getMovie(rental), rental))
            .sum();
  }

  private int calculateFrequentPoints(Movie movie, MovieRental rental) {
    return "new".equals(movie.getCode()) && rental.getDays() > 2
            ? 2
            : 1;
  }
}
