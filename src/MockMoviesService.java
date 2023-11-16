import java.util.Map;

public final class MockMoviesService implements MoviesService {

  private static final Map<String, Movie> movies = Map.of(
      "F001", new Movie("You've Got Mail", "regular"),
      "F002", new Movie("Matrix", "regular"),
      "F003", new Movie("Cars", "childrens"),
      "F004", new Movie("Fast & Furious X", "new"));

  private static final double regularPrice = 2;
  private static final double childrensPrice = 1.5;

  @Override
  public Movie getMovieById(String movieId) {
    return movies.get(movieId);
  }

  @Override
  public double calculateRentPrice(String movieCode, int days) {
    return switch (movieCode) {
      case "regular" -> days > 2 ? ((days - 2) * 1.5) + regularPrice : regularPrice;
      case "new" -> days * 3;
      case "childrens" -> days > 3 ? ((days - 3) * 1.5) + childrensPrice : childrensPrice;
      default -> 0;
    };
  }

}
