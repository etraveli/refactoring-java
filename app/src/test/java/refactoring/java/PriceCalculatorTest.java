package refactoring.java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import refactoring.java.model.MovieCategory;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    @Test
    void computePriceAndPointsChildren() {
        PriceCalculator priceCalculator = new PriceCalculator();

        PriceCalculationResult result = priceCalculator.computePriceAndPoints(MovieCategory.CHILDRENS, 7);
        assertEquals(7.5, result.getPrice());
        assertEquals(1, result.getPoints());
    }

    @Test
    void computePriceAndPointsNew() {
        PriceCalculator priceCalculator = new PriceCalculator();

        PriceCalculationResult result = priceCalculator.computePriceAndPoints(MovieCategory.NEW, 4);
        assertEquals(12.0, result.getPrice());
        assertEquals(2, result.getPoints());
    }

    @Test
    void computePriceAndPointsRegular() {
        PriceCalculator priceCalculator = new PriceCalculator();

        PriceCalculationResult result = priceCalculator.computePriceAndPoints(MovieCategory.REGULAR, 1);
        assertEquals(2.0, result.getPrice());
        assertEquals(1, result.getPoints());
    }

}