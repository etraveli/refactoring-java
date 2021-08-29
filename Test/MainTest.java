import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void CustomerMissing() {
        String expectedResult = "Customer name is missing!";

        String actualResult = new RentalInfo().statement(null);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void MovieMissing() {
        // Movie type not found test

        String expectedResult = "Rental Record for C. U. Stomer\n" +
                                 "Movie Type not found!";

        String actualResult = new RentalInfo().statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F006", 1))));
        assertEquals(expectedResult, actualResult);
    }

}