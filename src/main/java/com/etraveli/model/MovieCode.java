package com.etraveli.model;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Is used to categorize movie by different codes, but also provide way to calculate rental amount and frequent enter points from provided rental days.
 */
public enum MovieCode {
    REGULAR(days -> days > 2 ? ((days - 2) * 1.5) + 2 : 2d, days -> 1),
    CHILDREN(days -> days > 3 ? ((days - 3) * 1.5) + 1.5 : 1.5, days -> 1),
    NEW(days -> days * 3d, days -> days > 2 ? 2 : 1);

    final Function<Integer, Double> amountEquation;
    final UnaryOperator<Integer> pointsEquation;

    MovieCode(Function<Integer, Double> amountEquation, UnaryOperator<Integer> pointsEquation) {
        this.amountEquation = amountEquation;
        this.pointsEquation = pointsEquation;
    }

    /**
     * Using appropriate equation for the MovieCode and passing value of day to calculate rental amount.
     *
     * @param days amount of days for the rental amount
     * @return rental amount result from the equation for the MovieCode
     */
    public double amount(int days) {
        return this.amountEquation.apply(days);
    }

    /**
     * Using appropriate equation for the MovieCode and passing value of day to calculate Frequent Enter Points.
     *
     * @param days amount of days for the rental Frequent Enter Points
     * @return frequent enter points result from the equation for the MovieCode
     */
    public int frequentEnterPoints(int days) {
        return this.pointsEquation.apply(days);
    }
}