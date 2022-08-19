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
class ChildrenTest {

    @InjectMocks
    private Children children;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(children, "rentalPrice", 1.5D);
        ReflectionTestUtils.setField(children, "dayThreshold", 3);
    }

    @Test
    void test_children_rental_price() {
        assertEquals(children.getRentalPrice(1), 1.5);
    }

    @Test
    void test_children_code() {
        assertEquals(MovieType.CHILDREN.toString(), children.code());
    }
}