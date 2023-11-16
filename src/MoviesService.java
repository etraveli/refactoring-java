public interface MoviesService {

  public Movie getMovieById(String movieId) throws Exception;

  public double calculateRentPrice(MovieCode movieCode, int days) throws Exception;

}
