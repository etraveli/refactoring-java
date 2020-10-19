import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Customer {
    private final String name;
    private List<MovieRental> rentals;

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }

    public String statement() {

        Map<String, Movie> movies = getTestMovies();

        double totalAmount = 0;
        int frequentEnterPoints = 0;

        StringBuilder result = new StringBuilder("Rental Record for " + name + "\n");

        for (MovieRental r : rentals) {

            double thisAmount;

            try {
                Movie movie = movies.get(r.getMovieId());
                String movieCode = movie.getCode();
                int days = r.getDays();

                thisAmount = getAmount(movieCode, days);

            } catch (Exception ex) {
                return "Error message: " + ex.getMessage();
            }

            //add frequent bonus points
            frequentEnterPoints++;

            // add bonus for a two day new release rental
            if (movies.get(r.getMovieId()).getCode().equals("new") && r.getDays() > 2) {
                frequentEnterPoints++;
            }

            //print figures for this rental
            result.append("\t").append(movies.get(r.getMovieId()).getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

        return result.toString();
    }

    private Map<String, Movie> getTestMovies() {
        return Map.of("F001", new Movie("F001","You've Got Mail", "regular"),
                "F002", new Movie("F002", "Matrix", "regular"),
                "F003", new Movie("F003", "Cars", "childrens"),
                "F004", new Movie("F004","Fast & Furious X", "new"));
    }

    /*
    public static Customer getTestData() {
        List<MovieRental> testDataRentals = Arrays.asList(
                new MovieRental("F001", 3), new MovieRental("F002", 1));
        return new Customer("C. U. Stomer", testDataRentals);
    }
     */

    private double getAmount(String code, int days) throws Exception{
        double amount = 0;

        if (code.equals("regular")) {
            amount = 2;
            if (days > 2) {
                amount = ((days - 2) * 1.5) + amount;
            }
        }
        else if (code.equals("new")) {
            amount = days * 3;
        }
        else if (code.equals("childrens")) {
            amount = 1.5;
            if (days > 3) {
                amount = ((days - 3) * 1.5) + amount;
            }
        }
        if (amount == 0) {
            throw new Exception("Could not calculate amount due");
        }
        return amount;
    }
}