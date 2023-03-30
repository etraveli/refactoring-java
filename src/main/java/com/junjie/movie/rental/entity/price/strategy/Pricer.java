package com.junjie.movie.rental.entity.price.strategy;

public interface Pricer {
    double getRentExpense(int rentDays);

    int getFrequencyEnterPoints(int rentDays);
}
