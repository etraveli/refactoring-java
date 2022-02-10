package refactoring.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static refactoring.java.model.MovieCategory.*;

class LoyaltyPointsCalculatorTest {

    @Test
    void computePointsChildren() {
        LoyaltyPointsCalculator loyaltyPointsCalculator = new LoyaltyPointsCalculator();

        int points = loyaltyPointsCalculator.computePoints(CHILDRENS, 7);
        assertEquals(1, points);
    }

    @Test
    void computePointsNew() {
        LoyaltyPointsCalculator loyaltyPointsCalculator = new LoyaltyPointsCalculator();

        int points = loyaltyPointsCalculator.computePoints(NEW, 4);
        assertEquals(2, points);
    }

    @Test
    void computePointsRegular() {
        LoyaltyPointsCalculator loyaltyPointsCalculator = new LoyaltyPointsCalculator();

        int points = loyaltyPointsCalculator.computePoints(REGULAR, 1);
        assertEquals(1, points);
    }

}