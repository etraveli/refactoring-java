package enums;

/**
 * Enum to get assigned to movie model, enums as well have
 * individual configuration which are used to calculate total and frequentEnterPoints
 */
public enum MovieCode {
    REGULAR(2,2, -2, 1.5, null),
    CHILDREN(0,null, 0, 3, null),
    NEW(1.5,  3, -3, 1.5, 2);

    /**
     * starting amount of the ticket determined by movie code
     */
    private double startingAmount;
    /**
     * days to compare with @MovieRental.getDays()
     * if null the condition does not take place
     */
    private Integer daysToCompare ;
    /**
     * value to be added to amount
     */
    private int valueToBeAdded;
    /**
     * value that amount should be multiplied  with
     */
    private double valueToBeMultiplied;
    /**
     * Days to compare with @MovieRental.getDays() and determine if another extra point should be added
     * if null the condition does not take place
     */
    private Integer daysToCompareForFrequentEnterPoints;

    /**
     * Constructor for dynamic parameterized enum for movie code,
     * in which we can create new enum, and is going to affect
     * the logic of the amount and frequentEnterPoints
     * @param startingAmount
     * @param daysToCompare
     * @param valueToBeAdded
     * @param valueToBeMultiplied
     * @param daysToCompareForFrequentEnterPoints
     */
    MovieCode(double startingAmount, Integer daysToCompare,
              int valueToBeAdded, double valueToBeMultiplied,
              Integer daysToCompareForFrequentEnterPoints) {
        this.startingAmount = startingAmount;
        this.daysToCompare = daysToCompare;
        this.valueToBeAdded = valueToBeAdded;
        this.valueToBeMultiplied = valueToBeMultiplied;
        this.daysToCompareForFrequentEnterPoints = daysToCompareForFrequentEnterPoints;
    }

    public double getStartingAmount() {
        return startingAmount;
    }

    public Integer getDaysToCompare() {
        return daysToCompare;
    }

    public int getValueToBeAdded() {
        return valueToBeAdded;
    }

    public double getValueToBeMultiplied() {
        return valueToBeMultiplied;
    }

    public Integer getDaysToCompareForFrequentEnterPoints() {
        return daysToCompareForFrequentEnterPoints;
    }
}
