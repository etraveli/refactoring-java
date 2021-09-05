package helper;

import entity.Movie;
import enums.MovieType;

import java.util.HashMap;

public class MovieHelper {

    public static HashMap<String, Movie> generateMoviesMap() {

        HashMap<String, Movie> movies = new HashMap();
        movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieType.REGULAR));
        movies.put("F003", new Movie("Cars", MovieType.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW));
        return movies;

    }

}
