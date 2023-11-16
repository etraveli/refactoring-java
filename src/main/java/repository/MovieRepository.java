package repository;

import entity.Movie;

public interface MovieRepository {
   Movie getMovie(String movieCode);

}
