import customer.Customer;
import customer.CustomerRepo;
import movie.rental.RentalInfo;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        try {
            String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
            HashMap<String, Customer> customers = new CustomerRepo().fetch().toHashMap();
            String result = new RentalInfo().statement(customers.get("C. U. Stomer"));

            if (!result.equals(expected)) {
                throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected)
                        + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
            }

            System.out.println("Success");
        } catch (Exception exc) {
            System.out.println("Failure with error: '" + exc.getMessage() + "'");
        }
    }
}
