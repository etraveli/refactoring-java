package serviceImpl;

import config.RentalConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegularPriceTest {
    @BeforeEach
    void setUp() {
        RentalConfig.setupConfigByConfigFileName("/config.properties");
    }

    @Test
    public void given_RentAmount_When_RentedDaysIsFour_Then_RentAmountIsFive() {
        Double expected = 5.0;
        RegularPrice price = new RegularPrice();
        Double result = price.getRentAmount(4);
        assertEquals(result, expected);
    }

    @Test
    public void given_RentAmount_When_RentedDaysIsZero_Then_RentAmountIsTwo() {
        Double expected = 2.0;
        RegularPrice price = new RegularPrice();
        Double result = price.getRentAmount(0);
        assertEquals(result, expected);
    }

    @Test
    public void given_RentAmount_When_RentedDaysIsThree_Then_RentAmountIsThreePointFive() {
        Double expected = 3.5;
        RegularPrice price = new RegularPrice();
        Double result = price.getRentAmount(3);
        assertEquals(result, expected);
    }

    @Test
    public void given_RentAmount_When_RentedDaysIsMinus_Then_RentAmountIsTwo() {
        Double expected = 2.0;
        RegularPrice price = new RegularPrice();
        Double result = price.getRentAmount(-1);
        assertEquals(result, expected);
    }

    @Test
    public void given_FrequentRentedPoints_When_RentedDaysIsFour_Then_FrequentRentedPointsIsOne() {
        int expected = 1;
        RegularPrice price = new RegularPrice();
        int result = price.getFrequentRentedPoints(4);
        assertEquals(result, expected);
    }

    @Test
    public void given_FrequentRentedPoints_When_RentedDaysIsMinus_Then_FrequentRentedPointsIsOne() {
        int expected = 1;
        RegularPrice price = new RegularPrice();
        int result = price.getFrequentRentedPoints(-4);
        assertEquals(result, expected);
    }

    @Test
    public void given_FrequentRentedPoints_When_RentedDaysIsZero_Then_FrequentRentedPointsIsOne() {
        int expected = 1;
        RegularPrice price = new RegularPrice();
        int result = price.getFrequentRentedPoints(0);
        assertEquals(result, expected);
    }

}