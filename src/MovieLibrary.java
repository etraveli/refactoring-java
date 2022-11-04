import models.Movie;
import models.enums.MovieCode;

import java.util.HashMap;

public class MovieLibrary {
    HashMap<String, Movie> movies = new HashMap();

    public MovieLibrary() {
        movies.put("F001", new Movie("You've Got Mail", MovieCode.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieCode.REGULAR));
        movies.put("F003", new Movie("Cars", MovieCode.CHILDREN));
        movies.put("F004", new Movie("Fast & Furious X", MovieCode.NEW));
    }

    public Movie getMovieById(String id) {
        return movies.get(id);
    }
}
