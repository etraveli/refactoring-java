package test;

import main.Customer;
import main.MovieRental;
import main.RentalInfo;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RentalInfoTest{

    @Test
    public void statement_expectSuccess_test(){
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        String result = new RentalInfo().statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        assertEquals(expected,result);

    }


    @Test
    public void statement_expectFailure_test(){
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 6.5\nYou earned 6 frequent points\n";

        String result = new RentalInfo().statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

        assertNotEquals(expected,result);
    }
}