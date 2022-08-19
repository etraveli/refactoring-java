package com.etraveli.pattern.types;

import com.etraveli.constants.MovieType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Regular implements MovieRentalType {

    @Value("${regular.rental-price}")
    private double rentalPrice;

    @Value("${regular.day-threshold}")
    private int dayThreshold;

    @Override
    public double getRentalPrice(int numberOfDays) {
        double thisAmount = dayThreshold;
        if (numberOfDays > dayThreshold) {
            thisAmount = ((numberOfDays - dayThreshold) * rentalPrice) + thisAmount;
        }
        return thisAmount;
    }

    @Override
    public String code() {
        return MovieType.REGULAR.toString();
    }
}
