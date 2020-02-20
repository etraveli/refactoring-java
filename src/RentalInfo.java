import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RentalInfo {

    // Constants to be used by both impl and test
    public static final String REGULAR = "regular";
    public static final String NEW = "new";
    public static final String CHILDREN = "children";

    // Movies DB
    private Map<String, Movie> movies;
    // Map of "Movie Type" and "Amount Calculator Function"
    private Map<String, Function<Integer, Double>> calculators;

    private void initIfNeeded(){
        // Populate movies DB with default movies if not already set from outside
        if (movies == null){
            initMovies();
        }
        // Populate amount calculators map if not already set from outside
        if (calculators == null){
            initCalculators();
        }
    }

    private void initMovies(){
        movies = new HashMap<String, Movie>();
        movies.put("F001", new Movie("You've Got Mail", REGULAR));
        movies.put("F002", new Movie("Matrix", REGULAR));
        movies.put("F003", new Movie("Cars", CHILDREN));
        movies.put("F004", new Movie("Fast & Furious X", NEW));
    }

    private void initCalculators(){
        calculators = new HashMap<String, Function<Integer, Double>>();
        calculators.put(REGULAR, calcRegular);
        calculators.put(NEW, calcNew);
        calculators.put(CHILDREN, calcChildren);
    }

    public String statement(Customer customer) {

        initIfNeeded();

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        String result = "Rental Record for " + customer.getName() + "\n";
        for (MovieRental rental : customer.getRentals()) {
          double thisAmount = 0;

          // Check if this movie exists in DB, otherwise create a default one
          Movie currentMovie = movies.getOrDefault(rental.getMovieId(),
              new Movie("Unknown Movie ("+rental.getMovieId()+")", REGULAR ));

          // determine amount for each movie
          thisAmount = calculators.get(currentMovie.getCode()).apply(rental.getDays());

          //add frequent bonus points
          frequentEnterPoints++;
          // add bonus for a two day new release rental
          frequentEnterPoints += bonusCalculator.apply(currentMovie.getCode(), rental.getDays());

          //print figures for this rental
          result += "\t" + currentMovie.getTitle() + "\t" + thisAmount + "\n";
          totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentEnterPoints + " frequent points\n";
        return result;
    }

    //
    /**
     * Default bonus calculator that receives movie type and number of rental days
     * as input and returns 0 or 1 based on the logic
     */
    private BiFunction<String,Integer,Integer> bonusCalculator = (movieType, days) -> {
        if (NEW.equals(movieType) && days > 2) {
            return 1;
        }else {
            return 0;
        }
    };

    /**
     * Default amount calculator for REGULAR movies that receives the number of rental days
     * and returns a Double as the amount
     */
    private static final Function<Integer, Double> calcRegular = (days) -> {
        double thisAmount = 2;
        if (days > 2) {
            thisAmount = ((days - 2) * 1.5) + thisAmount;
        }
        return thisAmount;
    };

    /**
     * Default amount calculator for NEW movies that receives the number of rental days
     * and returns a Double as the amount
     */
    private static final Function<Integer, Double> calcNew = (days) -> (double)days * 3;

    /**
     * Default amount calculator for CHILDREN movies that receives the number of rental days
     * and returns a Double as the amount
     */
    private static final Function<Integer, Double> calcChildren = (days) -> {
        double thisAmount = 1.5;
        if (days > 3) {
            thisAmount = ((days - 3) * 1.5) + thisAmount;
        }
        return thisAmount;
    };

    /**
     * To set movies map from outside like test cases and so on if needed
     */
    public RentalInfo setMovies(Map<String, Movie> movies) {
        this.movies = movies;
        return this;
    }

    /**
     * To set amount calculators map from outside like test cases and so on if needed
     */
    public RentalInfo setCalculators(Map<String, Function<Integer, Double>> calculators) {
        this.calculators = calculators;
        return this;
    }

    /**
     * To set a custom bonus calculator function from outside like test cases and so on if needed
     */
    public RentalInfo setBonusCalculator(BiFunction<String,Integer,Integer> bonusCalculator) {
        this.bonusCalculator = bonusCalculator;
        return this;
    }
}
