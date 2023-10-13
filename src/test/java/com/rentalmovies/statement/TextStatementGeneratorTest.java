package com.rentalmovies.statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.rentalmovies.rentalservice.RentCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TextStatementGeneratorTest
{
    private TextStatementGenerator generator;

    @BeforeEach
    void setUp()
    {
        generator = new TextStatementGenerator(mock(RentCalculationService.class));
    }

    @Test
    void testHeader()
    {
        assertEquals("Rental Record for Bob\n", generator.header("Bob"));
    }

    @Test
    void testDetail()
    {
        assertEquals("\tHello brother\t3.0\n", generator.detail("Hello brother", 3.0));
    }

    @Test
    void testFooter()
    {
        assertEquals("Amount owed is 10.0\nYou earned 2 frequent points\n", generator.footer(10.0, 2));
    }
}
