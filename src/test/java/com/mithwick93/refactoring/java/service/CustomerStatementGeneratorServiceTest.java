package com.mithwick93.refactoring.java.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerStatementGeneratorServiceTest {

    @Test
    void givenInitializedProperly_whenGenerate_thenReturnCorrectStatement() {
        CustomerStatementGeneratorService customerStatementGeneratorService = new CustomerStatementGeneratorService("John Doe");
        customerStatementGeneratorService.addMovieStatement("The Reverent", 3.0, 1);

        String expected = """
                Rental Record for John Doe
                	The Reverent	3.0
                Amount owed is 3.0
                You earned 1 frequent points
                """;

        String result = customerStatementGeneratorService.generate();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void givenInitializedNotProperly_whenGenerate_thenReturnDefaultStatement() {
        CustomerStatementGeneratorService customerStatementGeneratorService = new CustomerStatementGeneratorService("");

        String expected = """
                Rental Record for\s
                Amount owed is 0.0
                You earned 0 frequent points
                """;

        String result = customerStatementGeneratorService.generate();

        assertNotNull(result);
        assertEquals(expected, result);
    }
}