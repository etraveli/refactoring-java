import java.util.HashMap;

public class RentalInfo {

    private static final HashMap<Movie.Id, Movie> movieStock;

    static {
        movieStock = new HashMap<>();
        movieStock.put(Movie.Id.F001, new Movie(Movie.Id.F001, "You've Got Mail", Movie.Category.REGULAR));
        movieStock.put(Movie.Id.F002, new Movie(Movie.Id.F002, "Matrix", Movie.Category.REGULAR));
        movieStock.put(Movie.Id.F003, new Movie(Movie.Id.F003, "Cars", Movie.Category.CHILDREN));
        movieStock.put(Movie.Id.F004, new Movie(Movie.Id.F004, "Fast & Furious X", Movie.Category.NEW));
    }

    public static HashMap<Movie.Id, Movie> getMovieStock() {
        return movieStock;
    }

    public Statement getStatement(Customer customer) {
        Statement statement = new Statement(customer.getName());

        customer.getRentals().forEach(rental -> {
            Movie movie = movieStock.get(rental.getMovieId());
            if (movie == null) {
                return;
            }

            double rentalAmount = 0;
            switch (movie.getCategory()) {
                case REGULAR:
                    rentalAmount = calculateRegularRentalAmount(rental.getDays());
                    break;
                case CHILDREN:
                    rentalAmount = calculateChildrenRentalAmount(rental.getDays());
                    break;
                case NEW:
                    rentalAmount = calculateNewReleaseRentalAmount(rental.getDays());
                    if (rental.getDays() > 2) // add bonus for a two-day new release rental
                        statement.incrementFrequentEnterPoints();
                    break;
            }

            statement.incrementFrequentEnterPoints();
            statement.addRental(movie.getTitle(), rentalAmount);
        });

        return statement;
    }

    private double calculateNewReleaseRentalAmount(int days) {
        return days * 3;
    }

    private static double calculateChildrenRentalAmount(int days) {
        double amount = 1.5;
        if (days > 3) {
            amount += (days - 3) * 1.5;
        }
        return amount;
    }

    private static double calculateRegularRentalAmount(int days) {
        double amount = 2;
        if (days > 2) {
            amount += (days - 2) * 1.5;
        }
        return amount;
    }
}

