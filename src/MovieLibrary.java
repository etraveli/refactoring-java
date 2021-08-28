import java.util.HashMap;

public class MovieLibrary {
    private final HashMap<String, Movie> movies = new HashMap();

    public MovieLibrary() {
        movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR));  //"F001" Key in the Hashmap
        movies.put("F002", new Movie("Matrix", MovieType.REGULAR));
        movies.put("F003", new Movie("Cars", MovieType.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW));
    }

    public HashMap<String, Movie>  getMoviesFromLibrary() {
        return movies;
    }
}