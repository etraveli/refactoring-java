package com.etraveli.pattern.types;

import com.etraveli.constants.MovieType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Children implements MovieRentalType {

    @Value(value = "${children.rental-price}")
    private double rentalPrice;

    @Value(value = "${children.day-threshold}")
    private int dayThreshold;

    @Override
    public double getRentalPrice(int numberOfDays) {
        double thisAmount = rentalPrice;
        if (numberOfDays > dayThreshold) {
            thisAmount = ((numberOfDays - dayThreshold) * rentalPrice) + thisAmount;
        }
        return thisAmount;
    }

    @Override
    public String code() {
        return MovieType.CHILDREN.toString();
    }

}
