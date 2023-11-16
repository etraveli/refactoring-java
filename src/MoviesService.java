import java.math.BigDecimal;

public interface MoviesService {

  public Movie getMovieById(String movieId) throws Exception;

  public BigDecimal calculateRentPrice(MovieCode movieCode, int days) throws Exception;

}
