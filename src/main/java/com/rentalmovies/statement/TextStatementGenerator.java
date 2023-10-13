package com.rentalmovies.statement;

/**
 * Separation of Concerns: Each part of the statement is now constructed in its own method,
 * making the code cleaner and easier to modify or extend.
 */
public class TextStatementGenerator extends AbstractStatementGenerator
{
    @Override
    protected String header(String customerName)
    {
        return "Rental Record for " + customerName + "\n";
    }

    @Override
    protected String detail(String movieTitle, double rentalAmount)
    {
        return "\t" + movieTitle + "\t" + rentalAmount + "\n";
    }

    @Override
    protected String footer(double totalAmount, int frequentRenterPoints)
    {
        return "Amount owed is " + totalAmount + "\n"
                + "You earned " + frequentRenterPoints + " frequent points\n";
    }
}
