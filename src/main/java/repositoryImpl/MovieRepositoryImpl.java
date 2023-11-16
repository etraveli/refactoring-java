package repositoryImpl;

import entity.Movie;
import repository.MovieRepository;
import util.MapMovieToCode;

public class MovieRepositoryImpl implements MovieRepository {
    @Override
    public Movie getMovie(String movieCode) {
        return MapMovieToCode.getMovie(movieCode);
    }
}
