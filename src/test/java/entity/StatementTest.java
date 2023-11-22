package entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StatementTest {

    @ParameterizedTest
    @MethodSource("provideAmountToCalculate")
    void testGetTotalAmount(double expectedTotalAmount, double[] amounts) {
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
    void testToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rental Record for C. U. Stomer").append("\n");
        sb.append("\t").append(Movie.F002.getTitle()).append("\t").append("3.5").append("\n");
        sb.append("\t").append(Movie.F004.getTitle()).append("\t").append("2.0").append("\n");
        sb.append("Amount owed is 5.5").append("\n");
        sb.append("You earned 2 frequent points").append("\n");

        Statement result = new Statement("C. U. Stomer");
        result.addRental(Movie.F002.getTitle(), 3.5);
        result.addRental(Movie.F004.getTitle(), 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();
        assertEquals(sb.toString(), result.toString());
    }

    @Test
    void testEquals() {
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
    void testNotEqualsDiffInFrequentEnterPoints() {
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
    void testNotEqualsDiffNumberOfRentals() {
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
    void testNotEqualsDiffDifferentRentals() {
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
    void testNotEqualsDiffCustomerName() {
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