package utils;

import pojo.Movie;

import java.util.HashMap;
import java.util.Map;

public class MoviesDB {

    Constants objConstants = new Constants();
    public Map<String, Movie> getMovies(){

        HashMap<String, Movie> movies = new HashMap();
        movies.put("F001", new Movie("You've Got Mail", objConstants.REGULAR));
        movies.put("F002", new Movie("Matrix", objConstants.REGULAR));
        movies.put("F003", new Movie("Cars", objConstants.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", objConstants.NEW));

        return movies;
    }
}
