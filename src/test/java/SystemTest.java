import config.RentalConfig;
import entity.Customer;
import entity.MovieRental;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.MovieRepository;
import repositoryImpl.MovieRepositoryImpl;
import service.Receipt;
import serviceImpl.ReceiptString;
import serviceImpl.RentalInfoString;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemTest {
    private static final String CONFIG_FILE = "/config.properties";

    @BeforeAll
    public static void init() {
        RentalConfig.setupConfigByConfigFileName(CONFIG_FILE);
    }

    @Test
    public void given_Statement_When_CustomerRentsOnlyRegularFilms_Then_TotalOwnedIsBasedOnRegularFilmsPrice() {
        String expected = "Rental Record for customer1\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        MovieRepository repository =new MovieRepositoryImpl();
        MovieRental movieRentalF001=new MovieRental(1L, "F001", 3,repository.getMovie("F001"));
        MovieRental movieRentalF002=new MovieRental(3L,"F002", 1,repository.getMovie("F002"));
        List<MovieRental> MovieRentalList= Arrays.asList(movieRentalF001,movieRentalF002);
        Customer customer = new Customer(1L, "customer1", MovieRentalList);

        Receipt<String> receipt = new ReceiptString();
        String result = new RentalInfoString(customer, receipt).statement();
        assertEquals(result, expected);
    }

    @Test
    public void given_Statement_When_CustomerRentsOnlyChildren_Then_TotalOwnedIsBasedOnChildrenPrice() {
        String expected = "Rental Record for customer1\n\tCars\t1.5\nAmount owed is 1.5\nYou earned 1 frequent points\n";

        MovieRepository repository =new MovieRepositoryImpl();
        MovieRental movieRentalF003=new MovieRental(2L,"F003", 3,repository.getMovie("F003"));
        List<MovieRental> MovieRentalList= Arrays.asList(movieRentalF003);
        Customer customer = new Customer(1L, "customer1", MovieRentalList);

        Receipt<String> receipt = new ReceiptString();
        String result = new RentalInfoString(customer, receipt).statement();
        assertEquals(result, expected);
    }

    @Test
    public void given_Statement_When_CustomerRentsOnlyNew_Then_TotalOwnedIsBasedOnChildrenPrice() {
        String expected = "Rental Record for customer1\n\tFast & Furious X\t9.0\nAmount owed is 9.0\nYou earned 2 frequent points\n";

        MovieRepository repository =new MovieRepositoryImpl();
        MovieRental movieRentalF004=new MovieRental(1L, "F004", 3,repository.getMovie("F004"));
        List<MovieRental> MovieRentalList= Arrays.asList(movieRentalF004);
        Customer customer = new Customer(1L, "customer1", MovieRentalList);

        Receipt<String> receipt = new ReceiptString();
        String result = new RentalInfoString(customer, receipt).statement();
        assertEquals(result, expected);
    }

    @Test
    public void given_Statement_When_TheInputIsNull_Then_ShouldSelectCustomer() {
        String expected = "Please select a customer!";
        Receipt<String> receipt = new ReceiptString();
        String result = new RentalInfoString(null, receipt).statement();
        assertEquals(result, expected);
    }

    @Test
    public void given_Statement_When_CustomerRentsNothing_Then_TotalOwnedIsZero() {
        String expected = "Rental Record for customer1\nIs Zero";
        Customer customer = new Customer(1L, "customer1", null);
        Receipt<String> receipt = new ReceiptString();
        String result = new RentalInfoString(customer, receipt).statement();
        assertEquals(result, expected);
    }

    @Test
    public void given_Statement_When_CustomerRentsRegularFilmsAndNew_Then_TotalOwnedIsBasedOnRegularFilmsAndNewPrice() {
        String expected = "Rental Record for customer1\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\n\tFast & Furious X\t9.0\nAmount owed is 14.5\nYou earned 4 frequent points\n";
        MovieRepository repository =new MovieRepositoryImpl();
        MovieRental movieRentalF001=new MovieRental(1L, "F001", 3,repository.getMovie("F001"));
        MovieRental movieRentalF004=new MovieRental(2L,"F004", 3,repository.getMovie("F004"));
        MovieRental movieRentalF002=new MovieRental(3L,"F002", 1,repository.getMovie("F002"));
        List<MovieRental> MovieRentalList= Arrays.asList(movieRentalF001,movieRentalF002,movieRentalF004);
        Customer customer = new Customer(1L, "customer1", MovieRentalList);

        Receipt<String> receipt = new ReceiptString();
        String result = new RentalInfoString(customer, receipt).statement();
        assertEquals(result, expected);
    }

    @Test
    public void given_Statement_When_CustomerRentsRegularFilmsAndChildren_Then_TotalOwnedIsBasedOnRegularFilmAndChildrenPrice() {
        String expected = "Rental Record for customer1\n\tYou've Got Mail\t3.5\n\tCars\t1.5\nAmount owed is 5.0\nYou earned 2 frequent points\n";

        MovieRepository repository =new MovieRepositoryImpl();
        MovieRental movieRentalF001=new MovieRental(1L, "F001", 3,repository.getMovie("F001"));
        MovieRental movieRentalF003=new MovieRental(2L,"F003", 1,repository.getMovie("F003"));
        List<MovieRental> MovieRentalList= Arrays.asList(movieRentalF001,movieRentalF003);
        Customer customer = new Customer(1L, "customer1", MovieRentalList);

        Receipt<String> receipt = new ReceiptString();
        String result = new RentalInfoString(customer, receipt).statement();
        assertEquals(result, expected);
    }

    @Test
    public void given_Statement_When_CustomerRentsRegularFilmAndChildren_Then_TotalOwnedIsBasedOnRegularFilmsAndChildrenPrice() {
        String expected = "Rental Record for customer1\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\n\tCars\t1.5\nAmount owed is 7.0\nYou earned 3 frequent points\n";

        MovieRepository repository =new MovieRepositoryImpl();
        MovieRental movieRentalF001=new MovieRental(1L, "F001", 3,repository.getMovie("F001"));
        MovieRental movieRentalF003=new MovieRental(2L,"F003", 3,repository.getMovie("F003"));
        MovieRental movieRentalF002=new MovieRental(3L,"F002", 1,repository.getMovie("F002"));
        List<MovieRental> MovieRentalList= Arrays.asList(movieRentalF001,movieRentalF002,movieRentalF003);
        Customer customer = new Customer(1L, "customer1", MovieRentalList);

        Receipt<String> receipt = new ReceiptString();
        String result = new RentalInfoString(customer, receipt).statement();
        assertEquals(result, expected);
    }
}