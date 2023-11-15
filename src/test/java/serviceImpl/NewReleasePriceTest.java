package serviceImpl;

import config.RentalConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewReleasePriceTest {
    @BeforeEach
    void setUp() {
        RentalConfig.setupConfigByConfigFileName("/config.properties");
    }
    @Test
    public void given_RentAmount_When_RentedDaysIsTwo_Then_RentAmountIsSix() {
        Double expected = 6.0;
        NewReleasePrice price = new NewReleasePrice();
        Double result = price.getRentAmount(2);
        assertEquals(result, expected);
    }

    @Test
    public void given_RentAmount_When_RentedDaysIsZero_Then_RentAmountOnePointZero() {
        Double expected = 0.0;
        NewReleasePrice price = new NewReleasePrice();
        Double result = price.getRentAmount(0);
        assertEquals(result, expected);
    }

    @Test
    public void given_RentAmount_When_RentedDaysIsThree_Then_RentAmountIsNine() {
        Double expected = 9.0;
        NewReleasePrice price = new NewReleasePrice();
        Double result = price.getRentAmount(3);
        assertEquals(result, expected);
    }

    @Test
    public void given_RentAmount_When_RentedDaysIsMinus_Then_RentAmountIsZero() {
        Double expected = 0.0;
        NewReleasePrice price = new NewReleasePrice();
        Double result = price.getRentAmount(-1);
        assertEquals(result, expected);
    }

    @Test
    public void given_FrequentRentedPoints_When_RentedDaysIsFour_Then_FrequentRentedPointsIsTwo() {
        int expected = 2;
        NewReleasePrice price = new NewReleasePrice();
        int result = price.getFrequentRentedPoints(4);
        assertEquals(result, expected);
    }

    @Test
    public void given_FrequentRentedPoints_When_RentedDaysIsMinus_Then_FrequentRentedPointsIsOne() {
        int expected = 1;
        NewReleasePrice price = new NewReleasePrice();
        int result = price.getFrequentRentedPoints(-4);
        assertEquals(result, expected);
    }
    @Test
    public void given_FrequentRentedPoints_When_RentedDaysIsThree_Then_FrequentRentedPointsIsTwo() {
        int expected = 2;
        NewReleasePrice price = new NewReleasePrice();
        int result = price.getFrequentRentedPoints(3);
        assertEquals(result, expected);
    }

    @Test
    public void given_FrequentRentedPoints_When_RentedDaysIsZero_Then_FrequentRentedPointsIsOne() {
        int expected = 1;
        NewReleasePrice price = new NewReleasePrice();
        int result = price.getFrequentRentedPoints(0);
        assertEquals(result, expected);
    }

}