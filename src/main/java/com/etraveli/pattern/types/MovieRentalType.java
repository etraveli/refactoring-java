package com.etraveli.pattern.types;

public interface MovieRentalType {

    /**
     * @param numberOfDays
     * @return the rental price for give number of days - MovieType (code) is determined based on the implementations
     */
    double getRentalPrice(int numberOfDays);

    /**
     * @return code which represents the MovieType
     */
    String code();

}
