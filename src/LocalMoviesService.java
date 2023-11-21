import java.math.BigDecimal;
import java.util.Map;

public final class LocalMoviesService implements MoviesService {

  private static final Map<String, Movie> movies = Map.of(
      "F001", new Movie("You've Got Mail", MovieCode.REGULAR),
      "F002", new Movie("Matrix", MovieCode.REGULAR),
      "F003", new Movie("Cars", MovieCode.CHILDRENS),
      "F004", new Movie("Fast & Furious X", MovieCode.NEW));

  private static final BigDecimal regularPrice = new BigDecimal("2.0");
  private static final BigDecimal childrensPrice = new BigDecimal("1.5");

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
  public BigDecimal calculateRentPrice(MovieCode movieCode, int days) throws Exception {
    if (days <= 0)
      throw new Exception("Invalid days");
    if (movieCode == null)
      throw new Exception("Invalid movie code");
    return switch (movieCode) {
      case REGULAR ->
        days > 2 ? new BigDecimal(days - 2).multiply(new BigDecimal("1.5")).add(regularPrice) : regularPrice;
      case NEW -> new BigDecimal(days * 3);
      case CHILDRENS ->
        days > 3 ? new BigDecimal(days - 3).multiply(new BigDecimal("1.5")).add(childrensPrice) : childrensPrice;
      default -> throw new Exception("Invalid movie code");
    };
  }

}
