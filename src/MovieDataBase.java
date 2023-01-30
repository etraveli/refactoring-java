import java.util.HashMap;

/*
 * created a separate class to act as the "Database" from which the data comes to the application.
 */
public class MovieDataBase
{
    private HashMap<String, Movie> movies = new HashMap<String, Movie>();
    private HashMap<String, Double> priceList= new HashMap<String, Double>();
    //Added type arguments to avoid unchecked or unsafe operations
    public MovieDataBase() {
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));
        priceList.put("regular", 2.0);
        priceList.put("new", 3.0);
        priceList.put("children", 1.5);
    }
    public Movie getMovie(String id) {
        return movies.get(id);
    }
    public double getPrice(String movieType) {
        return priceList.get(movieType);
    }
}
