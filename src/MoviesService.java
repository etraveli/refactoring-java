public interface MoviesService {

  public Movie getMovieById(String movieId) throws Exception;

  public double calculateRentPrice(String movieCode, int days) throws Exception;

}
