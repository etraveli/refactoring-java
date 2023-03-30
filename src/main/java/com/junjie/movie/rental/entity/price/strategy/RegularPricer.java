package com.junjie.movie.rental.entity.price.strategy;

import com.junjie.movie.rental.entity.price.strategy.Pricer;

public class RegularPricer implements Pricer {
    @Override
    public double getRentExpense(int rentDays) {
        double expense = 2;
        if (rentDays > 2) {
            expense += (rentDays - 2) * 1.5;
        }
        return expense;
    }

    @Override
    public int getFrequencyEnterPoints(int rentDays) {
        return 1;
    }
}
