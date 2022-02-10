package refactoring.java.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static refactoring.java.model.MovieCategory.*;

class LoyaltyPointsCalculatorImplTest {

    @Test
    void computePointsChildren() {
        LoyaltyPointsCalculatorImpl loyaltyPointsCalculator = new LoyaltyPointsCalculatorImpl();

        int points = loyaltyPointsCalculator.computePoints(CHILDRENS, 7);
        assertEquals(1, points);
    }

    @Test
    void computePointsNew() {
        LoyaltyPointsCalculatorImpl loyaltyPointsCalculator = new LoyaltyPointsCalculatorImpl();

        int points = loyaltyPointsCalculator.computePoints(NEW, 4);
        assertEquals(2, points);
    }

    @Test
    void computePointsRegular() {
        LoyaltyPointsCalculatorImpl loyaltyPointsCalculator = new LoyaltyPointsCalculatorImpl();

        int points = loyaltyPointsCalculator.computePoints(REGULAR, 1);
        assertEquals(1, points);
    }

}