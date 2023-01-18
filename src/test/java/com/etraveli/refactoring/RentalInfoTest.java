package com.etraveli.refactoring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.etraveli.refactoring.repository.CustomerDB;
import com.etraveli.refactoring.repository.MovieDB;
import com.etraveli.refactoring.service.RentalInfo;;

public class RentalInfoTest 
{    
    private final static String CUSTOMER_ID = "C001";
    private final static String CUSTOMER_NAME = "C. U. Stomer";
    private final static String REGULAR_MOVIE_ID = "F001";
    private final static String REGULAR_MOVIE_TITLE = "You've Got Mail";
    private final static String CHILDRENS_MOVIE_ID = "F003";
    private final static String CHILDRENS_MOVIE_TITLE = "Cars";
    private final static String NEW_MOVIE_ID = "F004";
    private final static String NEW_MOVIE_TITLE = "Fast & Furious X";

    private RentalInfo rentalInfo;

    @BeforeEach
    public void init() {
        rentalInfo = new RentalInfo(new MovieDB(), new CustomerDB());
        rentalInfo.createCustomer(CUSTOMER_ID, CUSTOMER_NAME);
    }

    @AfterEach
    public void teardown() {
        rentalInfo = null;
    }
    @ParameterizedTest(name = "{index} => days={0}")
    @ValueSource(ints = {1, 2})
    public void test_renting_regular_cost_should_not_depend_on_days_if_2_or_less(int days) {
        //Arrange
        String expected = expectedInfo(CUSTOMER_NAME, "1", "2.0", new MovieCost(REGULAR_MOVIE_TITLE, "2.0") );
        rentalInfo.addRental(CUSTOMER_ID, REGULAR_MOVIE_ID, days);
        
        //Act
        String result = rentalInfo.statement(CUSTOMER_ID);
        
        //Assert
        assertEquals(expected, result);
    }
    @ParameterizedTest(name = "{index} => days={0}, cost={1}")
    @CsvSource({
        "1, 2.0",
        "2, 2.0",
        "3, 3.5",
        "4, 5.0",
        "5, 6.5",
        "6, 8.0",
    })
    public void test_renting_regular_cost_should_depend_on_days_if_more_than_2(int days, String cost) {
        //Arrange
        String expected = expectedInfo(CUSTOMER_NAME, "1", cost, new MovieCost(REGULAR_MOVIE_TITLE, cost) );
        rentalInfo.addRental(CUSTOMER_ID, REGULAR_MOVIE_ID, days);
        
        //Act
        String result = rentalInfo.statement(CUSTOMER_ID);
        
        //Assert
        assertEquals(expected, result);
    }
    @ParameterizedTest(name = "{index} => days={0}, cost={1}")
    @ValueSource(ints = {1, 2, 3})
    public void test_renting_childrens_cost_should_not_depend_on_days_if_3_or_less(int days) {
        //Arrange
        String expected = expectedInfo(CUSTOMER_NAME, "1", "1.5", new MovieCost(CHILDRENS_MOVIE_TITLE, "1.5") );
        rentalInfo.addRental(CUSTOMER_ID, CHILDRENS_MOVIE_ID, days);
        
        //Act
        String result = rentalInfo.statement(CUSTOMER_ID);
        
        //Assert
        assertEquals(expected, result);
    }
    @ParameterizedTest(name = "{index} => days={0}, cost={1}")
    @CsvSource({
        "4, 3.0",
        "5, 4.5",
        "6, 6.0",
    })
    public void test_renting_childrens_cost_should_depend_on_days_if_more_than_3(int days, String cost) {
        //Arrange
        String expected = expectedInfo(CUSTOMER_NAME, "1", cost, new MovieCost(CHILDRENS_MOVIE_TITLE, cost) );
        rentalInfo.addRental(CUSTOMER_ID, CHILDRENS_MOVIE_ID, days);
        
        //Act
        String result = rentalInfo.statement(CUSTOMER_ID);
        
        //Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "{index} => days={0}, cost={1}")
    @CsvSource({
        "1, 3.0",
        "2, 6.0"
    })
    public void statement_OneNewMovie_CostShouldDependOnDays(int days, String cost) {
        //Arrange
        String expected = expectedInfo(CUSTOMER_NAME, "1", cost, new MovieCost(NEW_MOVIE_TITLE, cost) );
        rentalInfo.addRental(CUSTOMER_ID, NEW_MOVIE_ID, days);
        
        //Act
        String result = rentalInfo.statement(CUSTOMER_ID);
        
        //Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "{index} => days={0}, cost={1}, points={2}")
    @CsvSource({
        "3, 9.0",
        "4, 12.0"
    })
    public void test_statement_renting_new_for_more_than_two_days_should_give_two_points(int days, String cost) {
            //Arrange
            String points = "2";
            String expected = expectedInfo(CUSTOMER_NAME, points, cost, new MovieCost(NEW_MOVIE_TITLE, cost) );
            rentalInfo.addRental(CUSTOMER_ID, NEW_MOVIE_ID, days);
            
            //Act
            String result = rentalInfo.statement(CUSTOMER_ID);
            
            //Assert
            assertEquals(expected, result);
        }

    @Test
    public void test_renting_2_cost_and_points_should_add_up() {
        //Arrange
        String expected = expectedInfo(CUSTOMER_NAME, "2", "8.0", new MovieCost(REGULAR_MOVIE_TITLE, "2.0"), new MovieCost(NEW_MOVIE_TITLE, "6.0"));
        rentalInfo.addRental(CUSTOMER_ID, REGULAR_MOVIE_ID, 1);
        rentalInfo.addRental(CUSTOMER_ID, NEW_MOVIE_ID, 2);
        
        //Act
        String result = rentalInfo.statement(CUSTOMER_ID);
        
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void originalTest() {
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        RentalInfo rentalInfo = new RentalInfo(new MovieDB(), new CustomerDB());
        rentalInfo.createCustomer("C001", "C. U. Stomer");
        rentalInfo.addRental("C001", "F001", 3);
        rentalInfo.addRental("C001", "F002", 1);
        String result = rentalInfo.statement("C001");
    
        assertTrue(result.equals(expected));
    }

    // Help Functions
    private String expectedInfo(String name, String points, String owed, MovieCost... movies){
        String recordPart = "Rental Record for C. U. Stomer" + System.lineSeparator();
        
        StringBuilder sb = new StringBuilder();
		for (MovieCost movie : movies)
			sb.append("\t" + movie.name).append("\t" + movie.cost+ System.lineSeparator());
        String moviesPart = sb.toString();
    
        String owedPart = "Amount owed is " + owed +  System.lineSeparator();
        String pointsPart = "You earned " + points + " frequent points" + System.lineSeparator();
        String expected = recordPart + moviesPart + owedPart + pointsPart;

        return expected;
    }

    private class MovieCost{
            final String name;
            final String cost;
            MovieCost(String name, String cost){
                this.name = name;
                this.cost = cost;
            }
    }
}
