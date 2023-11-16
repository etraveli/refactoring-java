package repositoryImpl;

import entity.Movie;
import repository.MovieRepository;
import util.MapMovieToID;

public class MovieRepositoryImpl implements MovieRepository {
    @Override
    public Movie getMovie(String movieId) {
        return MapMovieToID.getMovie(movieId);
    }
}
