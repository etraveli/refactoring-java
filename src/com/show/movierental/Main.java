package com.show.movierental;

import com.show.movierental.core.Customer;
import com.show.movierental.core.Movie;
import com.show.movierental.core.Rental;
import com.show.movierental.presentation.RentalConsoleView;
import com.show.movierental.presentation.RentalViewController;
import com.show.movierental.repository.InMemoryMovieRepository;
import com.show.movierental.repository.MovieRepository;
import com.show.movierental.service.RentalService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MovieRepository movieRepository = new InMemoryMovieRepository();
        RentalService rentalService = new RentalService(movieRepository);
        RentalConsoleView rentalConsoleView = new RentalConsoleView();
        RentalViewController rentalViewController = new RentalViewController(rentalService, rentalConsoleView);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.println("Available Movies:");
        for (Map.Entry<String, Movie> entry : movieRepository.getMovies().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getTitle());
        }

        List<Rental> rentals = new ArrayList<>();
        while (true) {
            System.out.print("Enter movie ID (or 'done' to finish): ");
            String movieId = scanner.nextLine();
            if (movieId.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter rental days: ");
            int rentalDays = Integer.parseInt(scanner.nextLine());

            rentals.add(new Rental(movieId, rentalDays));
        }

        Customer customer = new Customer(customerName, rentals);
        rentalViewController.generateAndDisplayRentalStatement(customer);
    }
}

