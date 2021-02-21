package test;

import main.Customer;
import main.MovieRental;
import main.RentalService;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RentalServiceTest{

    @Test
    public void statement_accurateDetails_expectSuccess_test(){
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        String result = new RentalService().getStatement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        assertEquals(expected,result);

    }


    @Test
    public void statement_emptyMovieRentals_expectEmptyRental_test(){
        String expected = "Rental records for customer not found.";

        String result = new RentalService().getStatement(new Customer("C. U. Stomer", Collections.emptyList()));

        assertEquals(expected,result);
    }
    @Test
    public void statement_nullMovieRentals_expectRecordsNotFound_test(){
        String expected = "Rental records for customer not found.";

        String result = new RentalService().getStatement(new Customer("C. U. Stomer", null));

        assertEquals(expected,result);
    }


    @Test
    public void statement_wrongRentalId_expectNoSuchRental_test(){
        String expected = "Rental Record for C. U. Stomer\n" +
                "\tF008\t: No such rental. Check your details.\n" +
                "\tMatrix\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent points\n";

        String result = new RentalService().getStatement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F008", 3), new MovieRental("F002", 1))));

        assertEquals(expected,result);

    }

    /*
    *
    * Add a new rental record in the 'movies' list setting the code to null to check the below test case
    *
    * */
    @Test
    public void statement_unAvailableRentalInfo_expectNotAvailable_test(){
        String expected = "Rental Record for C. U. Stomer\n\tF005\t: Not available at the moment.\n\tMatrix\t2.0\nAmount owed is 2.0\nYou earned 1 frequent points\n";

        String result = new RentalService().getStatement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F005", 3), new MovieRental("F002", 1))));

        assertEquals(expected,result);

    }

}