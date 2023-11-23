package entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StatementTest {
    @ParameterizedTest
    @MethodSource("provideAmountToCalculate")
    public void givenHavingRentals_whenGettingTheTotalAmount_thenSumUpAllRentalAmounts(double expectedTotalAmount, double[] amounts) {
        Statement result = new Statement("C. U. Stomer");
        for (double amount : amounts) {
            result.addRental(Movie.F002.getTitle(), amount);
        }

        assertEquals(expectedTotalAmount, result.getTotalAmount());
    }

    private static Stream<Arguments> provideAmountToCalculate() {
        return Stream.of(
                Arguments.of(5.5, new double[]{2.0, 3.5}),
                Arguments.of(11.8, new double[]{2.0, 3.5, 4.2, 2.1}),
                Arguments.of(19.9, new double[]{2.0, 3.5, 1.5, 12.9}),
                Arguments.of(2.0, new double[]{2.0})
        );
    }

    @Test
    public void givenStatement_whenCallingToString_thenReturnEasyReadString() {
        String sb = "Rental Record for C. U. Stomer" + "\n" +
                "\t" + Movie.F002.getTitle() + "\t" + "3.5" + "\n" +
                "\t" + Movie.F004.getTitle() + "\t" + "2.0" + "\n" +
                "Amount owed is 5.5" + "\n" +
                "You earned 2 frequent points" + "\n";

        Statement result = new Statement("C. U. Stomer");
        result.addRental(Movie.F002.getTitle(), 3.5);
        result.addRental(Movie.F004.getTitle(), 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();
        assertEquals(sb, result.toString());
    }

    @Test
    public void givenStatementsDoNotDiffer_whenComparing_thenReturnEqual() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental(Movie.F002.getTitle(), 3.5);
        expected.addRental(Movie.F004.getTitle(), 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        Statement result = new Statement("C. U. Stomer");
        result.addRental(Movie.F002.getTitle(), 3.5);
        result.addRental(Movie.F004.getTitle(), 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();

        assertEquals(expected, result);
    }

    @Test
    public void givenFrequentEnterPointsDiffers_whenComparing_thenReturnNotEqual() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental(Movie.F002.getTitle(), 3.5);
        expected.addRental(Movie.F004.getTitle(), 2.0);
        expected.incrementFrequentEnterPoints();

        Statement result = new Statement("C. U. Stomer");
        result.addRental(Movie.F002.getTitle(), 3.5);
        result.addRental(Movie.F004.getTitle(), 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();

        assertNotEquals(expected, result);
    }

    @Test
    public void givenRentalCountDiffers_whenComparing_thenReturnNotEqual() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental(Movie.F002.getTitle(), 3.5);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        Statement result = new Statement("C. U. Stomer");
        result.addRental(Movie.F002.getTitle(), 3.5);
        result.addRental(Movie.F004.getTitle(), 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();

        assertNotEquals(expected, result);
    }

    @Test
    public void givenRentalsDiffers_whenComparing_thenReturnNotEqual() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental(Movie.F002.getTitle(), 3.5);
        expected.addRental(Movie.F004.getTitle(), 3.5);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        Statement result = new Statement("C. U. Stomer");
        result.addRental(Movie.F002.getTitle(), 3.5);
        result.addRental(Movie.F001.getTitle(), 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();

        assertNotEquals(expected, result);
    }

    @Test
    public void givenCustomerDiffers_whenComparing_thenReturnNotEqual() {
        Statement expected = new Statement("C. U. Stomer NOT");
        expected.addRental(Movie.F002.getTitle(), 3.5);
        expected.addRental(Movie.F004.getTitle(), 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        Statement result = new Statement("C. U. Stomer");
        result.addRental(Movie.F002.getTitle(), 3.5);
        result.addRental(Movie.F004.getTitle(), 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();

        assertNotEquals(expected, result);
    }
}