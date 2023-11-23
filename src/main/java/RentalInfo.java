import entity.Customer;
import entity.Movie;
import entity.Statement;

public class RentalInfo {

    public Statement getStatement(Customer customer) {
        Statement statement = new Statement(customer.getName());

        customer.getRentals().forEach(rental -> {
            Movie movie = rental.getMovie();

            double rentalAmount = calculateRentalAmount(movie.getCategory(), rental.getDays());

            // add bonus for a two-day new release rental
            if (movie.getCategory().equals(Movie.Category.NEW) && rental.getDays() > 2) {
                statement.incrementFrequentEnterPoints();
            }
            statement.incrementFrequentEnterPoints();

            statement.addRental(movie.getTitle(), rentalAmount);
        });

        return statement;
    }

    private double calculateRentalAmount(Movie.Category category, int days) {
        switch (category) {
            case REGULAR:
                return calculateRegularRentalAmount(days);
            case CHILDREN:
                return calculateChildrenRentalAmount(days);
            case NEW:
                return calculateNewReleaseRentalAmount(days);
            default:
                throw new UnsupportedOperationException("Unsupported movie category: " + category);
        }
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

