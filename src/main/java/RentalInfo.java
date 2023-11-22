import entity.Customer;
import entity.Movie;
import entity.Statement;

public class RentalInfo {

    public Statement getStatement(Customer customer) {
        Statement statement = new Statement(customer.getName());

        customer.getRentals().forEach(rental -> {
            Movie movie = rental.getMovie();
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

