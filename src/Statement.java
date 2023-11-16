import java.util.Map;

public final class Statement {

  // mock movies service
  private static final Map<String, Movie> movies = Map.of(
      "F001", new Movie("You've Got Mail", "regular"),
      "F002", new Movie("Matrix", "regular"),
      "F003", new Movie("Cars", "childrens"),
      "F004", new Movie("Fast & Furious X", "new"));

  private static final double regularPrice = 2;
  private static final double childrensPrice = 1.5;

  private final Customer customer;

  private double totalAmount;
  private int frequentEnterPoints = 0;

  // temp
  private String rentedMovies = "";

  public Statement(Customer customer) {
    this.customer = customer;
    for (MovieRental movieRental : customer.getRentals()) {

      String movieId = movieRental.getMovieId();
      Movie movie = movies.get(movieId);
      String movieCode = movie.code();
      int days = movieRental.getDays();

      double rentPrice = switch (movieCode) {
        case "regular" -> days > 2 ? ((movieRental.getDays() - 2) * 1.5) + regularPrice : regularPrice;
        case "new" -> movieRental.getDays() * 3;
        case "childrens" -> days > 3 ? ((movieRental.getDays() - 3) * 1.5) + childrensPrice : childrensPrice;
        default -> 0;
      };

      // add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movieCode.equals("new") && movieRental.getDays() > 2)
        frequentEnterPoints++;

      // print figures for this rental
      rentedMovies += "\t" + movie.title() + "\t" + rentPrice + "\n";
      totalAmount = totalAmount + rentPrice;
    }
  }

  @Override
  public String toString() {
    return "Rental Record for " + customer.getName() + "\n" +
        rentedMovies +
        "Amount owed is " + totalAmount + "\n" +
        "You earned " + frequentEnterPoints + " frequent points\n";
  }

}
