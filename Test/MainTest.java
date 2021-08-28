import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MainTest {


    @Test
    public void CustomerMissing() {
        String expectedResult3 = "Customer name is missing!";

        String actualResult3 = new RentalInfo().statement(null);
        assertEquals(expectedResult3, actualResult3);

    }

    @Test
    public void MovieMissing() {
        // Movie type not found test

        String expectedResult2 = "Rental Record for C. U. Stomer\n" +
                                 "Movie Type not found!";

        String actualResult2 = new RentalInfo().statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F006", 1))));
        assertEquals(expectedResult2, actualResult2);
    }

}