import java.util.Map;

public final class MockMoviesService implements MoviesService {

  private static final Map<String, Movie> movies = Map.of(
      "F001", new Movie("You've Got Mail", MovieCode.REGULAR),
      "F002", new Movie("Matrix", MovieCode.REGULAR),
      "F003", new Movie("Cars", MovieCode.CHILDRENS),
      "F004", new Movie("Fast & Furious X", MovieCode.NEW));

  private static final double regularPrice = 2;
  private static final double childrensPrice = 1.5;

  @Override
  public Movie getMovieById(String movieId) throws Exception {
    if (movieId == null)
      throw new Exception("Invalid movie id");
    Movie movie = movies.get(movieId);
    if (movie == null)
      throw new Exception("Invalid movie id");
    return movie;
  }

  @Override
  public double calculateRentPrice(MovieCode movieCode, int days) throws Exception {
    if (days <= 0)
      throw new Exception("Invalid days");
    if (movieCode == null)
      throw new Exception("Invalid movie code");
    return switch (movieCode) {
      case REGULAR -> days > 2 ? ((days - 2) * 1.5) + regularPrice : regularPrice;
      case NEW -> days * 3;
      case CHILDRENS -> days > 3 ? ((days - 3) * 1.5) + childrensPrice : childrensPrice;
      default -> throw new Exception("Invalid movie code");
    };
  }

}
