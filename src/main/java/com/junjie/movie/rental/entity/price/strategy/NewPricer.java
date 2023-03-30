package com.junjie.movie.rental.entity.price.strategy;

public class NewPricer implements Pricer {
    @Override
    public double getRentExpense(int rentDays) {
        return rentDays * 3;
    }

    @Override
    public int getFrequencyEnterPoints(int rentDays) {
        return rentDays > 2 ? 2 : 1;
    }
}
