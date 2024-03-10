package com.mithwick93.refactoring.java.service.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StatementGeneratorHelperTest {

    @Test
    void givenInitializedProperly_whenGenerate_thenReturnCorrectStatement() {
        StatementGeneratorHelper statementGeneratorHelper = new StatementGeneratorHelper("John Doe");
        statementGeneratorHelper.addMovieStatement("The Reverent", 3.0, 1);

        String expected = """
                Rental Record for John Doe
                	The Reverent	3.0
                Amount owed is 3.0
                You earned 1 frequent points
                """;

        String result = statementGeneratorHelper.getStatementText();

        assertNotNull(result, "Statement should not be null");
        assertEquals(expected, result, "Statement should be as expected");
    }

    @Test
    void givenInitializedNotProperly_whenGenerate_thenReturnDefaultStatement() {
        StatementGeneratorHelper statementGeneratorHelper = new StatementGeneratorHelper("");

        String expected = """
                Rental Record for\s
                Amount owed is 0.0
                You earned 0 frequent points
                """;

        String result = statementGeneratorHelper.getStatementText();

        assertNotNull(result, "Statement should not be null");
        assertEquals(expected, result, "Statement should be as expected");
    }
}
