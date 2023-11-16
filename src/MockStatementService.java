import java.math.BigDecimal;
import java.util.List;

public final class MockStatementService extends StatementService {

  public MockStatementService(MoviesService moviesService) {
    super(moviesService);
  }

  @Override
  public Statement createStatement(String customerName, List<MovieRental> movieRentals) throws Exception {
    if (customerName == null || customerName.length() <= 0)
      throw new Exception("Invalid customer name");
    if (movieRentals == null)
      throw new Exception("Invalid movie rentals");
    Statement statement = new Statement(customerName);
    for (MovieRental movieRental : movieRentals) {
      Movie movie = moviesService.getMovieById(movieRental.movieId());
      MovieCode movieCode = movie.code();
      int days = movieRental.days();
      BigDecimal rentPrice = moviesService.calculateRentPrice(movieCode, days);
      boolean isTwoDayNewRelease = movieCode.equals(MovieCode.NEW) && days > 2;
      statement.addEntry(movie.title(), rentPrice, isTwoDayNewRelease);
    }
    return statement;
  }

}
