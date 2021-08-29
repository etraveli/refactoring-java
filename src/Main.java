/**
 * A program that creates a movie coupen with customer name and movies he/she has rented(with no of days).
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

public class Main {

    public static void main(String[] args) {
        //movieMenu();
        //userInput();
        movieRentalTest();
    }

    public static void userInput() {
        ArrayList<MovieRental> moviesRented = new ArrayList<MovieRental>();
        MovieLibrary movieLibrary = new MovieLibrary();
        RentalInfo rentalData = new RentalInfo();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter Customer name:");
        String CustomerName = input.next();
        Customer customer = new Customer(CustomerName, moviesRented);

        String movieId = "";
        do {
            System.out.println("Please enter movieId to rent it:");
            movieId = input.next();
            System.out.println("Number of days to rent:");
            int noOfdaysToRent = input.nextInt();

            if (!moviesRented.isEmpty()) {
                for (MovieRental movieCheck : moviesRented) {
                    if (movieCheck.getMovieId().compareToIgnoreCase(movieId) != 0 &&
                            (movieLibrary.getMoviesFromLibrary().containsKey(movieId))) {
                        //rentals.add(new MovieRental(movieId, noOfdaysToRent));
                        moviesRented.add(new MovieRental(movieId, noOfdaysToRent));
                        break;
                    } else {
                        if (!movieLibrary.getMoviesFromLibrary().containsKey(movieId)){
                            System.out.println("No movie with this Id exists..");
                        }
                        else
                            System.out.println("Movie already Rented. Please Select another movie.");
                    }
                }
            } else {
                if (movieLibrary.getMoviesFromLibrary().containsKey(movieId)) {
                    moviesRented.add(new MovieRental(movieId, noOfdaysToRent));
                }else {
                    System.out.println("No movie with this Id exists.");
                }
            }
            System.out.println("write 1 to keep renting (anything else to generate rental slip):");
        } while (input.next().compareTo("1") == 0);
        System.out.println(rentalData.statement(customer));

    }

    public static void movieMenu() {
        System.out.println("**************************");
        System.out.println("***Movies Rental System***");
        System.out.println("**************************");
        System.out.println("MovieId  |    MovieName:   ");
        System.out.println("F001     | You've Got Mail ");
        System.out.println("F002     | Matrix          ");
        System.out.println("F003     | Cars            ");
        System.out.println("F004     | Fast & Furious X");
    }

    public static void movieRentalTest() {
        String expectedResult = "Rental Record for C. U. Stomer" + "\n\t" +
                "You've Got Mail" + "\t" + "3.5" + "\n\t" +
                "Matrix" + "\t" + "2.0" + "\n" +
                "Amount owed is 5.5" + "\n" +
                "You earned 2 frequent points\n";
        String actualResult = new RentalInfo().statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));
        String conclude = !actualResult.equals(expectedResult) == TRUE ? " FAIL" : " PASS";

        System.out.println("***********" + "\t\t" + "Movie Test" + "\t\t" + "***********");
        System.out.println("**" + "\t" + "Test Name" + "\t\t\t\t\t" + "| Result **");
        System.out.println("Movie Rental Slip Creation" + "\t\t" + "| " + conclude + " **" + "\n");

        if (!actualResult.equals(expectedResult)) {
            throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expectedResult) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + actualResult);
        }
    }
}
