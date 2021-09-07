package helper;

import entity.Movie;
import enums.MovieType;

import java.util.HashMap;

/**
 * MovieHelper class which has Movie helper methods in it
 */
public class MovieHelper {

    public final static double INITIAL_AMOUNT = 0.0;

    /**
     * Generates Movies Map with movie types including initial amount values
     *
     * @return
     */
    public static HashMap<String, Movie> generateMoviesMap() {

        HashMap<String, Movie> movies = new HashMap();
        movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR, INITIAL_AMOUNT));
        movies.put("F002", new Movie("Matrix", MovieType.REGULAR, INITIAL_AMOUNT));
        movies.put("F003", new Movie("Cars", MovieType.CHILDRENS, INITIAL_AMOUNT));
        movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW, INITIAL_AMOUNT));
        return movies;
    }

}
