import config.RentalConfig;
import entity.Customer;
import entity.MovieRental;
import repository.MovieRepository;
import repositoryImpl.MovieRepositoryImpl;
import service.Receipt;
import serviceImpl.ReceiptString;
import serviceImpl.RentalInfoString;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String CONFIG_FILE = "/config.properties";

    public static void main(String[] args) {

        setupConfig();
        getRentalListOfCustomer();

    }

    private static void setupConfig() {
        RentalConfig.setupConfigByConfigFileName(CONFIG_FILE);
    }

    private static void getRentalListOfCustomer() {
        MovieRepository repository = new MovieRepositoryImpl();
        MovieRental movieRentalF001 = new MovieRental(1L, "F001", 3, repository.getMovie("F001"));
        MovieRental movieRentalF002 = new MovieRental(2L, "F002", 1, repository.getMovie("F002"));
        List<MovieRental> MovieRentalList = Arrays.asList(movieRentalF001, movieRentalF002);
        Customer customer = new Customer(1L, "C. U. Stomer", MovieRentalList);
        Receipt<String> receipt = new ReceiptString();
        new RentalInfoString(customer, receipt).statement();
    }
}
