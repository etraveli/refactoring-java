import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dedicated class (or layer) for accessing data. Sure in real cases the
 * implementation is different
 */

public class DataAccess {

    Map<String, Movie> moviesTable = new HashMap<String, Movie>();
    Map<String, Customer> customersTable = new HashMap<String, Customer>();

    // Populate dummy data, this step will not be there in real implementation
    public DataAccess() {

        moviesTable.put("F001", new Movie("You've Got Mail", Const.Code.REGULAR));
        moviesTable.put("F002", new Movie("Matrix", Const.Code.REGULAR));
        moviesTable.put("F003", new Movie("Cars", Const.Code.CHILDREN));
        moviesTable.put("F004", new Movie("Fast & Furious X", Const.Code.NEW));

        // Adding more customers for more test cases
        List<MovieRental> movieRentals1 = Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1));
        List<MovieRental> movieRentals2 = Arrays.asList(new MovieRental("F003", 5), new MovieRental("F004", 4));
        List<MovieRental> movieRentals3 = Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1));
        List<MovieRental> movieRentals4 = Arrays.asList();
        
        customersTable.put("C001", new Customer("C. U. Stomer", movieRentals1));
        customersTable.put("C002", new Customer("Adam K", movieRentals2));
        customersTable.put("C003", new Customer("John L", movieRentals3));
        customersTable.put("C004", new Customer("Jan G", movieRentals4));
    }

    public Map<String, Movie> getAllMovies() {
        return moviesTable;
    }

    public Customer getCustomerById(String customerId) {
        return customersTable.get(customerId);
    }

}