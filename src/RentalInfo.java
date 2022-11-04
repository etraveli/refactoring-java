import java.util.HashMap;

public class RentalInfo {

  public String statement(Customer customer) {

    MovieLibrary movieLibrary = new MovieLibrary();

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      //TODO extract this to strategy pattern
      // Seems like the logic could be dependent on any property of movie model
      if (movieLibrary.getMovieById(r.getMovieId()).getCode().equals("regular")) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (movieLibrary.getMovieById(r.getMovieId()).getCode().equals("new")) {
        thisAmount = r.getDays() * 3;
      }
      if (movieLibrary.getMovieById(r.getMovieId()).getCode().equals("childrens")) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movieLibrary.getMovieById(r.getMovieId()).getCode() == "new" && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movieLibrary.getMovieById(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
