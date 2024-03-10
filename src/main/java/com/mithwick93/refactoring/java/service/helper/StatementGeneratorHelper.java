package com.mithwick93.refactoring.java.service.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * StatementGeneratorHelper class to help generate the statement for the
 * customer.
 */
public class StatementGeneratorHelper {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String TAB = "\t";
    private static final int PRECISION = 1;

    private final String customerName;
    private final StringBuilder movieStatementsBuilder;
    private BigDecimal totalAmount;

    private int frequentPoints;

    public StatementGeneratorHelper(final String customerName) {
        this.customerName = customerName;
        this.movieStatementsBuilder = new StringBuilder();
        this.totalAmount = new BigDecimal("0.0")
                .setScale(PRECISION, RoundingMode.HALF_UP);
    }

    /**
     * Add a movie statement to the customer statement.
     *
     * @param name           name of the movie
     * @param rentalAmount   rental amount for the movie
     * @param frequentPoints frequent points for the movie
     */
    public void addMovieStatement(
            final String name,
            final BigDecimal rentalAmount,
            final int frequentPoints
    ) {
        this.movieStatementsBuilder
                .append(TAB)
                .append(name)
                .append(TAB)
                .append(rentalAmount)
                .append(LINE_SEPARATOR);
        this.totalAmount = this.totalAmount.add(rentalAmount);
        this.frequentPoints += frequentPoints;
    }

    /**
     * Get string representation of the customer statement.
     *
     * @return string representation of the customer statement
     */
    public String getStatementText() {
        String movieStatements = movieStatementsBuilder.toString();
        return "Rental Record for " + customerName + LINE_SEPARATOR
                + movieStatements
                + "Amount owed is " + totalAmount + LINE_SEPARATOR
                + "You earned " + frequentPoints + " frequent points"
                + LINE_SEPARATOR;
    }
}
