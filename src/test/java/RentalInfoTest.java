
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalInfoTest  {

    @Test
    public void  given_Statement_When_CustomerRentsOnlyRegularFilms_Then_TotalOwnedIsBasedOnRegularFilmsPrice(){
        String expected = "Rental Record for customer1\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
        List<MovieRental> MovieRentalList= Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1));
        Customer customer= new Customer("customer1",MovieRentalList) ;
        String result = new RentalInfo().statement(customer);
        assertEquals(result,expected);
    }
    @Test
    public void  given_Statement_When_CustomerRentsOnlyChildren_Then_TotalOwnedIsBasedOnChildrenPrice(){
        String expected = "Rental Record for customer1\n\tCars\t1.5\nAmount owed is 1.5\nYou earned 1 frequent points\n";
        List<MovieRental> MovieRentalList= Arrays.asList(new MovieRental("F003", 3));
        Customer customer= new Customer("customer1",MovieRentalList) ;
        String result = new RentalInfo().statement(customer);
        assertEquals(result,expected);
    }
    @Test
    public void  given_Statement_When_CustomerRentsOnlyNew_Then_TotalOwnedIsBasedOnChildrenPrice(){
        String expected = "Rental Record for customer1\n\tFast & Furious X\t9.0\nAmount owed is 9.0\nYou earned 2 frequent points\n";
        List<MovieRental> MovieRentalList= Arrays.asList(new MovieRental("F004", 3));
        Customer customer= new Customer("customer1",MovieRentalList) ;
        String result = new RentalInfo().statement(customer);
        assertEquals(result,expected);
    }
    @Test
    public void  given_Statement_When_TheInputIsNull_Then_ShouldSelectCustomer(){
        String expected = "Please select a customer!";
        String result = new RentalInfo().statement(null);
        assertEquals(result,expected);
    }
    @Test
    public void  given_Statement_When_CustomerRentsNothing_Then_TotalOwnedIsZero(){
        String expected = "Rental Record for customer1\nIs Zero";
        Customer customer= new Customer("customer1",null) ;
        String result = new RentalInfo().statement(customer);
        assertEquals(result,expected);
    }
    @Test
    public void  given_Statement_When_CustomerRentsRegularFilmsAndNew_Then_TotalOwnedIsBasedOnRegularFilmsAndNewPrice(){
        String expected = "Rental Record for customer1\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\n\tFast & Furious X\t9.0\nAmount owed is 14.5\nYou earned 4 frequent points\n";
        List<MovieRental> MovieRentalList= Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1),new MovieRental("F004", 3));
        Customer customer= new Customer("customer1",MovieRentalList) ;
        String result = new RentalInfo().statement(customer);
        assertEquals(result,expected);
    }
    @Test
    public void  given_Statement_When_CustomerRentsRegularFilmsAndChildren_Then_TotalOwnedIsBasedOnRegularFilmAndChildrenPrice(){
        String expected = "Rental Record for customer1\n\tYou've Got Mail\t3.5\n\tCars\t1.5\nAmount owed is 5.0\nYou earned 2 frequent points\n";
        List<MovieRental> MovieRentalList= Arrays.asList(new MovieRental("F001", 3), new MovieRental("F003", 2));
        Customer customer= new Customer("customer1",MovieRentalList) ;
        String result = new RentalInfo().statement(customer);
        assertEquals(result,expected);
    }
    @Test
    public void  given_Statement_When_CustomerRentsRegularFilmAndChildren_Then_TotalOwnedIsBasedOnRegularFilmsAndChildrenPrice(){
        String expected = "Rental Record for customer1\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\n\tCars\t1.5\nAmount owed is 7.0\nYou earned 3 frequent points\n";
        List<MovieRental> MovieRentalList= Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1),new MovieRental("F003", 3));
        Customer customer= new Customer("customer1",MovieRentalList) ;
        String result = new RentalInfo().statement(customer);
        assertEquals(result,expected);
    }
}