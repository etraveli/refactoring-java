import pojo.Customer;
import pojo.MovieRental;
import service.statement.GenerateStatement;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        List<MovieRental> movieRentals  = Arrays.asList((new MovieRental("F001", 3)),(new MovieRental("F002", 1)));

        Customer objCustomer = new Customer("C. U. Stomer", movieRentals);

        String result = new GenerateStatement().statement(objCustomer);

        if (!result.equals(expected)) {
            String error = String.format("Expected:%n%s%n%nGot:%n%s%n", expected, result);
            throw new AssertionError(error);
        }

        System.out.println("Success");
    }
}
