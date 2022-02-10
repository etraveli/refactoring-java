package refactoring.java.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static refactoring.java.model.MovieCategory.*;

class PriceCalculatorImplTest {

    @Test
    void computePriceAndPointsChildren() {
        PriceCalculatorImpl priceCalculator = new PriceCalculatorImpl();

        double price = priceCalculator.computePrice(CHILDRENS, 7);
        assertEquals(7.5, price);
    }

    @Test
    void computePriceAndPointsNew() {
        PriceCalculatorImpl priceCalculator = new PriceCalculatorImpl();

        double price = priceCalculator.computePrice(NEW, 4);
        assertEquals(12.0, price);
    }

    @Test
    void computePriceAndPointsRegular() {
        PriceCalculatorImpl priceCalculator = new PriceCalculatorImpl();

        double price = priceCalculator.computePrice(REGULAR, 1);
        assertEquals(2.0, price);
    }

}