package stock;

import java.util.HashMap;

public class Movies {

    private static Movies INSTANCE;
    private static HashMap<String, Movie> movies;

    private Movies() {
        movies = new HashMap();
        movies.put("F001", new Movie("You've Got Mail", PriceRange.REGULAR));
        movies.put("F002", new Movie("Matrix", PriceRange.REGULAR));
        movies.put("F003", new Movie("Cars", PriceRange.CHILDREN));
        movies.put("F004", new Movie("Fast & Furious X", PriceRange.NEW));
    }

    public static Movies getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Movies();
        }

        return INSTANCE;
    }

    public Movie getMovieByCode(String code) {
        return movies.get(code);
    }
}
