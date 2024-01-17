package com.etraveli.model;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public enum MovieCode {
    REGULAR(days -> days > 2 ? ((days - 2) * 1.5) + 2 : 2d, days -> 1),
    CHILDRENS(days -> days > 3 ? ((days - 3) * 1.5) + 1.5 : 1.5, days -> 1),
    NEW(days -> days * 3d, days -> days > 2 ? 2 : 1);

    final Function<Integer, Double> amountEquation;
    final UnaryOperator<Integer> pointsEquation;

    MovieCode(Function<Integer, Double> amountEquation, UnaryOperator<Integer> pointsEquation) {
        this.amountEquation = amountEquation;
        this.pointsEquation = pointsEquation;
    }

    public double amount(int days){
        return this.amountEquation.apply(days);
    }

    public int frequentEnterPoints(int days){
        return this.pointsEquation.apply(days);
    }
}