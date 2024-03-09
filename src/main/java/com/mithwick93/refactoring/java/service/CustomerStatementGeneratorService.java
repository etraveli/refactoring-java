package com.mithwick93.refactoring.java.service;

/**
 * CustomerStatementGeneratorService class to generate the statement for the customer
 */
public class CustomerStatementGeneratorService {
    private final static String LINE_SEPARATOR = System.lineSeparator();
    private final static String TAB = "\t";

    private final String customerName;
    private final StringBuilder movieStatementsBuilder;
    private double totalAmount;
    private int frequentPoints;

    public CustomerStatementGeneratorService(String customerName) {
        this.customerName = customerName;
        this.movieStatementsBuilder = new StringBuilder();
    }

    /**
     * Add a movie statement to the customer statement
     *
     * @param name           name of the movie
     * @param rentalAmount   rental amount for the movie
     * @param frequentPoints frequent points for the movie
     */
    public void addMovieStatement(String name, double rentalAmount, int frequentPoints) {
        this.movieStatementsBuilder
                .append(TAB).append(name).append(TAB).append(rentalAmount).append(LINE_SEPARATOR);
        this.totalAmount += rentalAmount;
        this.frequentPoints += frequentPoints;
    }

    /**
     * Get string representation of the customer statement
     *
     * @return string representation of the customer statement
     */
    public String generate() {
        String movieStatements = movieStatementsBuilder.toString();
        return "Rental Record for " + customerName + LINE_SEPARATOR +
                movieStatements +
                "Amount owed is " + totalAmount + LINE_SEPARATOR +
                "You earned " + frequentPoints + " frequent points" + LINE_SEPARATOR;
    }
}
