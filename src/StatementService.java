import java.util.List;

public abstract class StatementService {

  protected final MoviesService moviesService;

  public StatementService(MoviesService moviesService) {
    this.moviesService = moviesService;
  }

  public abstract Statement createStatement(String customerName, List<MovieRental> movieRentals) throws Exception;

}
