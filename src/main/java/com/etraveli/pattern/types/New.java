package com.etraveli.pattern.types;

import com.etraveli.constants.MovieType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class New implements MovieRentalType {

    @Value(value = "${new.rental-price}")
    private double rentalPrice;

    @Override
    public double getRentalPrice(int numberOfDays) {
        return numberOfDays * rentalPrice;
    }

    @Override
    public String code() {
        return MovieType.NEW.toString();
    }


}
