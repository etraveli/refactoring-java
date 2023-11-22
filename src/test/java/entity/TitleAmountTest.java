package entity;

import entity.TitleAmount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleAmountTest {

    @Test
    void testToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t").append("Fast & Furious X").append("\t").append("2.3").append("\n");

        TitleAmount result = new TitleAmount("Fast & Furious X", 2.3);
        assertEquals(sb.toString(), result.toString());
    }

    @Test
    void testEquals() {
        TitleAmount expected = new TitleAmount("Fast & Furious X", 2.3);
        TitleAmount result = new TitleAmount("Fast & Furious X", 2.3);
        assertEquals(expected, result);
    }
    @Test
    void testNotEqualsDiffInTitle() {
        TitleAmount expected = new TitleAmount("Fast & Furious X", 2.3);
        TitleAmount result = new TitleAmount("Matrix", 2.3);
        assertNotEquals(expected, result);
    }
    @Test
    void testNotEqualsDiffInPoints() {
        TitleAmount expected = new TitleAmount("Fast & Furious X", 2.3);
        TitleAmount result = new TitleAmount("Fast & Furious X", 2.0);
        assertNotEquals(expected, result);
    }
}