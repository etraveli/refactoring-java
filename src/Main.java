import models.Customer;
import models.MovieRental;
import service.RentalInfo;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        Customer customer = new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1)));
        RentalInfo rentalInfo = new RentalInfo();
        String result = rentalInfo.statement(customer);

        if (!result.equals(expected)) {
            throw new AssertionError("Test failed. Expected output:\n" + expected + "\nActual output:\n" + result);
        }
        System.out.println("Test passed: Success");
        System.out.println("Information Slip: "+ result);
    }
}
