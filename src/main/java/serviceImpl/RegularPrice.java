package serviceImpl;

import service.Price;
import config.PropertyKeyStatement;
import config.RentalConfig;

public class RegularPrice extends Price {
    @Override
    protected Double getPropertyBaseRentAmount(){
        return Double.parseDouble
                (RentalConfig.getPropertyByKey(PropertyKeyStatement.REGULAR_BASE_RENT_AMOUNT));
    }

    @Override
    protected Double getPropertyExtraRentAmount() {
        return Double.parseDouble
                (RentalConfig.getPropertyByKey(PropertyKeyStatement.REGULAR_EXTRA_RENT_AMOUNT));
    }

    @Override
    protected Integer getPropertyRentalDayLimit() {
        return Integer.parseInt
                (RentalConfig.getPropertyByKey(PropertyKeyStatement.REGULAR_RENTAL_DAY_LIMIT));
    }

    @Override
    protected Integer getPropertyFrequentRentedPoints() {
        return Integer.parseInt
                (RentalConfig.getPropertyByKey(PropertyKeyStatement.DEFAULT_FREQUENT_RENTED_POINTS));
    }

}
