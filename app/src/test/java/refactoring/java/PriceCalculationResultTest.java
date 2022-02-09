package refactoring.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculationResultTest {

    @Test
    void getPrice() {
        PriceCalculationResult priceCalculationResult = new PriceCalculationResult(1.5, 0);

        assertEquals(1.5, priceCalculationResult.getPrice());
    }

    @Test
    void getPoints() {
        PriceCalculationResult priceCalculationResult = new PriceCalculationResult(0.0, 12);

        assertEquals(12, priceCalculationResult.getPoints());
    }
}