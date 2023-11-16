package serviceImpl;

import config.PropertyKeyStatement;
import config.RentalConfig;
import lombok.Setter;
import service.Price;

@Setter
public class NewReleasePrice extends Price {
    private Integer rentalDayBonus;
    private Integer frequentRentedPointsBonus ;

    public Integer getRentalDayBonus() {
        if(rentalDayBonus==null)
            setRentalDayBonus(getPropertyRentalDayBonus());
        return rentalDayBonus;
    }

    private Integer getPropertyRentalDayBonus() {
       return Integer.parseInt
                (RentalConfig.getPropertyByKey(PropertyKeyStatement.NEW_RELEASE_RENTAL_DAY_BONUS));
    }

    public Integer getFrequentRentedPointsBonus() {
        if(frequentRentedPointsBonus==null)
            setFrequentRentedPointsBonus(getPropertyFrequentRentedPointsBonus());
        return frequentRentedPointsBonus;
    }

    private Integer getPropertyFrequentRentedPointsBonus() {
       return Integer.parseInt
                (RentalConfig.getPropertyByKey(PropertyKeyStatement.NEW_RELEASE_FREQUENT_RENTED_POINTS_BONUS));
    }

    @Override
    protected Double getPropertyBaseRentAmount(){
        return Double.parseDouble
                (RentalConfig.getPropertyByKey(PropertyKeyStatement.NEW_RELEASE_BASE_RENT_AMOUNT));
    }

    @Override
    protected Double getPropertyExtraRentAmount() {
        return Double.parseDouble
                (RentalConfig.getPropertyByKey(PropertyKeyStatement.NEW_RELEASE_EXTRA_RENT_AMOUNT));
    }

    @Override
    protected Integer getPropertyRentalDayLimit() {
        return Integer.parseInt
                (RentalConfig.getPropertyByKey(PropertyKeyStatement.NEW_RELEASE_RENTAL_DAY_LIMIT));
    }

    @Override
    protected Integer getPropertyFrequentRentedPoints() {
        return Integer.parseInt
                (RentalConfig.getPropertyByKey(PropertyKeyStatement.DEFAULT_FREQUENT_RENTED_POINTS));
    }



    @Override
    public Integer getFrequentRentedPoints(int rentedDays) {
        if (rentedDays > getRentalDayBonus())
            return getFrequentRentedPointsBonus();
        return super.getFrequentRentedPoints(rentedDays);
    }
}
