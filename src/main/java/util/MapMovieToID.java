package util;

import constant.ShowType;
import entity.Movie;
import entity.MovieType;
import serviceImpl.ChildrenPrice;
import serviceImpl.NewReleasePrice;
import serviceImpl.RegularPrice;

import java.util.HashMap;

public class MapMovieToID {
    static HashMap<String, Movie> movies;
    static {
        movies = new HashMap<>();
        MovieType  regularMovieType=new MovieType(1L, ShowType.REGULAR, new RegularPrice());
        MovieType  childrenMovieType=new MovieType(2L, ShowType.CHILDREN, new ChildrenPrice());
        MovieType  newReleaseMovieType=new MovieType(3L, ShowType.NEW_RELEASE, new NewReleasePrice());

        movies.put("F001", new Movie(1L,"You've Got Mail", regularMovieType));
        movies.put("F002", new Movie(2L,"Matrix", regularMovieType));
        movies.put("F003", new Movie(3L,"Cars", childrenMovieType));
        movies.put("F004", new Movie(4L,"Fast & Furious X", newReleaseMovieType));
    }
    public static Movie getMovie(String movieId){
        return movies.get(movieId);
    }
}
