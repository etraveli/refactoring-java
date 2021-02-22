package java;


import java.util.HashMap;
import java.util.Optional;


public class RentalService {

  private static final HashMap<String, Movie> movies =  new HashMap<String, Movie>() {{
            put("F001", new Movie("You've Got Mail", MovieCode.REGULAR )); //
            put("F002", new Movie("Matrix", MovieCode.REGULAR));
            put("F003", new Movie("Cars", MovieCode.CHILDRENS));
            put("F004", new Movie("Fast & Furious X", MovieCode.NEW));
          }};


  public String getStatement(Customer customer) {

    double totalAmount = 0;
    int bonusPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

    if(customer.getRentals() != null &&  !customer.getRentals().isEmpty()) {
      for (MovieRental r : customer.getRentals()) {
        double movieRentalAmount = 0;

        Optional<Movie> movie = Optional.ofNullable(movies.get(r.getMovieId()));

        //Check if such a rental exists, skip rental.
        if(movie.isEmpty()){
          result.append("\t").append(r.getMovieId()).append("\t").append(": No such rental. Check your details.").append("\n");
          continue;
        }

        MovieCode movieCode =  movies.get(r.getMovieId()).getCode();

        //if movie code is unavailable skip rental as the rental amount and bonus points depend on it.
        if(movieCode == null){
          result.append("\t").append(r.getMovieId()).append("\t").append(": Not available at the moment.").append("\n");
          continue;
        }

        // compute amount for this rental
        movieRentalAmount = computeRentalAmount(movieCode, r.getDays());

        //compute bonus
        bonusPoints = computeBonusPoints(movieCode, r.getDays(), bonusPoints);

        //print figures for this rental
        result.append("\t").append(movies.get(r.getMovieId()).getTitle()).append("\t").append(movieRentalAmount).append("\n");

        totalAmount = totalAmount + movieRentalAmount;
      }

      // add footer lines
      result.append("Amount owed is ").append(totalAmount).append("\n");
      result.append("You earned ").append(bonusPoints).append(" frequent points\n");
    }
    else
      result =  result.replace(0,result.length(), "Rental records for customer not found.");

    return result.toString();
  }


  private static double computeRentalAmount(MovieCode movieCode, int daysRented) {
    double movieRentalAmount = 0;
    if (movieCode.equals(MovieCode.REGULAR)) {
      movieRentalAmount = 2;
      if (daysRented > 2) {
        movieRentalAmount = ((daysRented - 2) * 1.5) + movieRentalAmount;
      }
    }
    if (movieCode.equals(MovieCode.NEW)) {
      movieRentalAmount = daysRented * 3;
    }
    if (movieCode.equals(MovieCode.CHILDRENS)) {
      movieRentalAmount = 1.5;
      if (daysRented > 3) {
        movieRentalAmount = ((daysRented - 3) * 1.5) + movieRentalAmount;
      }
    }
    return movieRentalAmount;
  }

  private static int computeBonusPoints(MovieCode movieCode, int daysRented, int bonusPoints){
    // add bonus for a two day new release rental
    if (movieCode.equals(MovieCode.NEW) && daysRented > 2)
      bonusPoints++;
    //add bonus for the rental
    return ++bonusPoints;
  }


}