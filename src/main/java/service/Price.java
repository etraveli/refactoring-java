package service;

import lombok.Setter;

//declared as an abstract class to prevent it from being initiated
@Setter
public abstract class Price {
    private Double baseRentAmount;
    private Double extraRentAmount;
    private Integer rentalDayLimit;
    private Integer frequentRentedPoints;

    public Integer getFrequentRentedPoints(int rentedDays) {
        return getFrequentRentedPoints();
    }

    public double getRentAmount(int rentedDays) {
        return rentedDays > getRentalDayLimit()
                ? ((rentedDays - getRentalDayLimit()) * getExtraRentAmount()) + getBaseRentAmount()
                : getBaseRentAmount();
    }

    public Double getBaseRentAmount() {
        if (baseRentAmount == null)
            setBaseRentAmount(getPropertyBaseRentAmount());
        return baseRentAmount;
    }

    abstract protected Double getPropertyBaseRentAmount();

    public Double getExtraRentAmount() {
        if (extraRentAmount == null)
            setExtraRentAmount(getPropertyExtraRentAmount());
        return extraRentAmount;
    }

    abstract protected Double getPropertyExtraRentAmount();

    public Integer getRentalDayLimit() {
        if (rentalDayLimit == null)
            setRentalDayLimit(getPropertyRentalDayLimit());
        return rentalDayLimit;
    }

    protected abstract Integer getPropertyRentalDayLimit();

    public Integer getFrequentRentedPoints() {
        if (frequentRentedPoints == null)
            setFrequentRentedPoints(getPropertyFrequentRentedPoints());
        return frequentRentedPoints;
    }

    protected abstract Integer getPropertyFrequentRentedPoints();
}
