import java.util.List;

public class RentalInfo {

  public String statement(Customer customer, List<MovieRental> movieRentalList) {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

    for (MovieRental movieRental : movieRentalList) {
      Movie movie = movieRental.getMovie();
      int rentalDays = movieRental.getTotalRentalDays();

      double thisAmount = calculateAmount(movie, rentalDays);
      frequentRenterPoints += calculateFrequentRenterPoints(movie, rentalDays);

      result.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;
    }

    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent points\n");
    return result.toString();
  }

  private double calculateAmount(Movie movie, int daysRented) {
    double amount = movie.getMovieType().getDefaultRentalPrice();
    if (daysRented > movie.getMovieType().getMaxRentalDays() && movie.getMovieType().getMaxRentalDays() > 0) {
      amount += (daysRented - movie.getMovieType().getMaxRentalDays()) * movie.getMovieType().getRentalPriceMultiplier();
    } else {
      amount = movie.getMovieType().getDefaultRentalPrice() * daysRented;
    }
    return amount;
  }

  private int calculateFrequentRenterPoints(Movie movie, int daysRented) {
    int points = 1;
    if (movie.getMovieType().getFrequentEnterPointsBonus() > 0 && daysRented > movie.getMovieType().getFrequentEnterPointsDays()) {
      points += movie.getMovieType().getFrequentEnterPointsBonus();
    }
    return points;
  }
}
