package com.etraveli.assignments.refactoring.util;

/** The enum Movie categories. */
public enum MovieCategory {
  /** Regular movie category. */
  REGULAR("regular", 2, 2, 1.5),
  /** New movie category. */
  NEW("new", 0, 0, 3),
  /** Childrens movie category. */
  CHILDRENS("childrens", 1.5, 3, 1.5);

  private final String code;
  private final double baseAmount;
  private final int defaultAllowedDays;
  private final double extraDaysMultiplier;

  MovieCategory(
      String code, double baseAmount, int defaultAllowedDays, double extraDaysMultiplier) {
    this.code = code;
    this.baseAmount = baseAmount;
    this.defaultAllowedDays = defaultAllowedDays;
    this.extraDaysMultiplier = extraDaysMultiplier;
  }

  /**
   * Gets the movie category code.
   *
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * Gets base amount for this movie category.
   *
   * @return the base amount
   */
  public double getBaseAmount() {
    return baseAmount;
  }

  /**
   * Gets default allowed days to keep for these types of movies.
   *
   * @return the default allowed days
   */
  public int getDefaultAllowedDays() {
    return defaultAllowedDays;
  }

  /**
   * Gets extra days multiplier.
   *
   * @return the extra days multiplier
   */
  public double getExtraDaysMultiplier() {
    return extraDaysMultiplier;
  }
}
