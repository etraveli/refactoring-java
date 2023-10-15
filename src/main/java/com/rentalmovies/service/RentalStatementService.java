package com.rentalmovies.service;

import com.rentalmovies.moviestore.MovieStoreDataAccess;
import com.rentalmovies.reportgenerationlogic.AbstractStatementGenerator;
import com.rentalmovies.reportgenerationlogic.TextStatementGenerator;

/**
 * The RentalStatementService class is responsible for generating rental
 * statements for customers. It utilizes an AbstractStatementGenerator to
 * create statements and a RentCalculationService to calculate the rental costs
 * and points earned.
 *
 * <p>This separation of concerns ensures that the class is only responsible
 * for coordinating the process of generating rental statements, making the
 * code more maintainable and testable.</p>
 *
 * Low coupling and High cohesion
 * Immutable os Thread Safe
 * @author Sajid Riaz
 */
public final class RentalStatementService
{
    private final AbstractStatementGenerator statementGenerator;
    private final RentCalculationService rentCalculationService;

    public RentalStatementService(MovieStoreDataAccess movieStore)
    {
        rentCalculationService = new RentCalculationService(movieStore);
        this.statementGenerator = new TextStatementGenerator(rentCalculationService);
    }

    public String getStatement(String customerId)
    {
        return statementGenerator.generateStatement(customerId);
    }
}
