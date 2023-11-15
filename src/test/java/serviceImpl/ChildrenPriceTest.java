package serviceImpl;

import config.PropertyKeyStatement;
import config.RentalConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChildrenPriceTest {

    @BeforeEach
    void setUp() {
        RentalConfig.setupConfigByConfigFileName("/config.properties");
    }

    @Test
    void testChildrenPriceInitialization() {
        ChildrenPrice childrenPrice = new ChildrenPrice();

        // Check if the properties are initialized correctly
        assertEquals(Double.parseDouble(RentalConfig.getPropertyByKey(PropertyKeyStatement.CHILDREN_BASE_RENT_AMOUNT)),
                childrenPrice.getBaseRentAmount());
        assertEquals(Integer.parseInt(RentalConfig.getPropertyByKey(PropertyKeyStatement.CHILDREN_RENTAL_DAY_LIMIT)),
                childrenPrice.getRentalDayLimit());
        assertEquals(Double.parseDouble(RentalConfig.getPropertyByKey(PropertyKeyStatement.CHILDREN_EXTRA_RENT_AMOUNT)),
                childrenPrice.getExtraRentAmount());
        assertEquals(Integer.parseInt(RentalConfig.getPropertyByKey(PropertyKeyStatement.DEFAULT_FREQUENT_RENTED_POINTS)),
                childrenPrice.getFrequentRentedPoints());
    }
    @Test
    public void given_RentAmount_When_RentedDaysIsFour_Then_RentAmountIsThree() {
        Double expected = 3.0;
        ChildrenPrice price = new ChildrenPrice();
        Double result = price.getRentAmount(4);
        assertEquals(result, expected);
    }

    @Test
    public void given_RentAmount_When_RentedDaysIsZero_Then_RentAmountIsOnePointFive() {
        Double expected = 1.5;
        ChildrenPrice price = new ChildrenPrice();
        Double result = price.getRentAmount(0);
        assertEquals(result, expected);
    }

    @Test
    public void given_RentAmount_When_RentedDaysIsThree_Then_RentAmountIsOnePointFive() {
        Double expected = 1.5;
        ChildrenPrice price = new ChildrenPrice();
        Double result = price.getRentAmount(3);
        assertEquals(result, expected);
    }

    @Test
    public void given_RentAmount_When_RentedDaysIsMinus_Then_RentAmountIsOnePointFive() {
        Double expected = 1.5;
        ChildrenPrice price = new ChildrenPrice();
        Double result = price.getRentAmount(-1);
        assertEquals(result, expected);
    }

    @Test
    public void given_FrequentRentedPoints_When_RentedDaysIsFour_Then_FrequentRentedPointsIsOne() {
        int expected = 1;
        ChildrenPrice price = new ChildrenPrice();
        int result = price.getFrequentRentedPoints(4);
        assertEquals(result, expected);
    }

    @Test
    public void given_FrequentRentedPoints_When_RentedDaysIsMinus_Then_FrequentRentedPointsIsOne() {
        int expected = 1;
        ChildrenPrice price = new ChildrenPrice();
        int result = price.getFrequentRentedPoints(-4);
        assertEquals(result, expected);
    }

    @Test
    public void given_FrequentRentedPoints_When_RentedDaysIsZero_Then_FrequentRentedPointsIsOne() {
        int expected = 1;
        ChildrenPrice price = new ChildrenPrice();
        int result = price.getFrequentRentedPoints(0);
        assertEquals(result, expected);
    }
}


