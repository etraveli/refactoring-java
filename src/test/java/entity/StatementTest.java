package entity;

import entity.Statement;
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
        Statement result = new Statement("");
        for (double amount : amounts) {
            result.addRental("", amount);
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
        sb.append("\t").append("Matrix").append("\t").append("3.5").append("\n");
        sb.append("\t").append("Fast & Furious X").append("\t").append("2.0").append("\n");
        sb.append("Amount owed is 5.5").append("\n");
        sb.append("You earned 2 frequent points").append("\n");

        Statement result = new Statement("C. U. Stomer");
        result.addRental("Matrix", 3.5);
        result.addRental("Fast & Furious X", 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();
        assertEquals(sb.toString(), result.toString());
    }

    @Test
    void testEquals() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("Matrix", 3.5);
        expected.addRental("Fast & Furious X", 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        Statement result = new Statement("C. U. Stomer");
        result.addRental("Matrix", 3.5);
        result.addRental("Fast & Furious X", 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();

        assertEquals(expected, result);
    }
    @Test
    void testNotEqualsDiffInFrequentEnterPoints() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("Matrix", 3.5);
        expected.addRental("Fast & Furious X", 2.0);
        expected.incrementFrequentEnterPoints();

        Statement result = new Statement("C. U. Stomer");
        result.addRental("Matrix", 3.5);
        result.addRental("Fast & Furious X", 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();

        assertNotEquals(expected, result);
    }
    @Test
    void testNotEqualsDiffNumberOfRentals() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("Matrix", 3.5);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        Statement result = new Statement("C. U. Stomer");
        result.addRental("Matrix", 3.5);
        result.addRental("Fast & Furious X", 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();

        assertNotEquals(expected, result);
    }
    @Test
    void testNotEqualsDiffDifferentRentals() {
        Statement expected = new Statement("C. U. Stomer");
        expected.addRental("Matrix", 3.5);
        expected.addRental("Fast & Furious X", 3.5);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        Statement result = new Statement("C. U. Stomer");
        result.addRental("Matrix", 3.5);
        result.addRental("You've Got Mail", 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();

        assertNotEquals(expected, result);
    }
    @Test
    void testNotEqualsDiffCustomerName() {
        Statement expected = new Statement("C. U. Stomer NOT");
        expected.addRental("Matrix", 3.5);
        expected.addRental("Fast & Furious X", 2.0);
        expected.incrementFrequentEnterPoints();
        expected.incrementFrequentEnterPoints();

        Statement result = new Statement("C. U. Stomer");
        result.addRental("Matrix", 3.5);
        result.addRental("Fast & Furious X", 2.0);
        result.incrementFrequentEnterPoints();
        result.incrementFrequentEnterPoints();

        assertNotEquals(expected, result);
    }
}