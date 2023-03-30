package com.junjie.movie.rental.entity.price.strategy;

public class ChildrenPricer implements Pricer {
    @Override
    public double getRentExpense(int rentDays) {
        double expense = 1.5;
        if (rentDays > 3) {
            expense += (rentDays - 3) * 1.5;
        }
        return expense;
    }

    @Override
    public int getFrequencyEnterPoints(int rentDays) {
        return 1;
    }
}
