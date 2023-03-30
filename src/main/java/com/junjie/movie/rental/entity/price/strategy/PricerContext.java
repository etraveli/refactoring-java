package com.junjie.movie.rental.entity.price.strategy;

import com.junjie.movie.rental.entity.enums.MovieType;

import java.util.HashMap;
import java.util.Map;

public class PricerContext {
    private Pricer pricer;

    public static Map<MovieType, Pricer> priceStrategyMap = new HashMap<>();

    static {
        priceStrategyMap.put(MovieType.REGULAR, new RegularPricer());
        priceStrategyMap.put(MovieType.NEW, new NewPricer());
        priceStrategyMap.put(MovieType.CHILDREN, new ChildrenPricer());
    }

    public PricerContext(MovieType movieType) {
        this.pricer = priceStrategyMap.get(movieType);
    }

    public double getRentExpense(int rentDays) {
        return this.pricer.getRentExpense(rentDays);
    }

    public int getFrequencyEnterPoints(int rentDays) {
        return this.pricer.getFrequencyEnterPoints(rentDays);
    }
}
