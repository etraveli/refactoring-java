package com.rentalmovies.reportgenerationlogic;

import com.rentalmovies.service.RentCalculationService;

/**
 * Separation of Concerns: Each part of the statement is now constructed in its own method,
 * making the code cleaner and easier to modify or extend.
 */
public class TextStatementGenerator extends AbstractStatementGenerator
{
    public TextStatementGenerator(final RentCalculationService service)
    {
        super(service);
    }

    String getHeader(final String customerName)
    {
        return "Rental Record for " + customerName + "\n";
    }

    @Override
    String getDetail(final String movieTitle, double rentalAmount)
    {
        return "\t" + movieTitle + "\t" + rentalAmount + "\n";
    }

    @Override
    String getFooter(double totalAmount, int frequentRenterPoints)
    {
        return "Amount owed is " + totalAmount + "\n"
                + "You earned " + frequentRenterPoints + " frequent points\n";
    }
}