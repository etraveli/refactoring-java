package com.etraveli.assignments.refactoring.util;

public enum MovieCategory {

    REGULAR("regular", 2, 2, 1.5),
    NEW("new", 0, 0, 3),
    CHILDRENS("childrens", 1.5, 3, 1.5);

    private final String code;
    private final double baseAmount;
    private final int defaultAllowedDays;
    private final double extraDaysMultiplier;

    MovieCategory(String code, double baseAmount, int defaultAllowedDays, double extraDaysMultiplier) {
        this.code = code;
        this.baseAmount = baseAmount;
        this.defaultAllowedDays = defaultAllowedDays;
        this.extraDaysMultiplier = extraDaysMultiplier;
    }

    public String getCode() {
        return code;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public int getDefaultAllowedDays() {
        return defaultAllowedDays;
    }

    public double getExtraDaysMultiplier() {
        return extraDaysMultiplier;
    }
}
