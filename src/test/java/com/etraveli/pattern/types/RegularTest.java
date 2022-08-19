package com.etraveli.pattern.types;

import com.etraveli.constants.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RegularTest {

    @InjectMocks
    private Regular regular;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(regular, "rentalPrice", 1.5D);
        ReflectionTestUtils.setField(regular, "dayThreshold", 2);
    }

    @Test
    public void test_regular_rental_price() {
        assertEquals(regular.getRentalPrice(3), 3.5);
    }

    @Test
    public void test_regular_code() {
        assertEquals(MovieType.REGULAR.toString(), regular.code());
    }
}