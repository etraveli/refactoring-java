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

            double rentalAmount;
            int rentalBonus;

            try {
                Movie movie = movies.get(r.getMovieId());
                String code = movie.getCode();
                rentalAmount = r.getAmount(code);
                rentalBonus = r.getBonusPoints(code);
            } catch (Exception ex) {
                return "Error message: " + ex.getMessage();
            }

            frequentEnterPoints += rentalBonus;

            //print figures for this rental
            result.append("\t").append(movies.get(r.getMovieId()).getTitle()).append("\t").append(rentalAmount).append("\n");
            totalAmount = totalAmount + rentalAmount;
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
}