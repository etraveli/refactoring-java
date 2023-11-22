package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleAmountTest {

    @Test
    void testToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t").append(Movie.F004.getTitle()).append("\t").append("2.3").append("\n");

        TitleAmount result = new TitleAmount(Movie.F004.getTitle(), 2.3);
        assertEquals(sb.toString(), result.toString());
    }

    @Test
    void testEquals() {
        TitleAmount expected = new TitleAmount(Movie.F004.getTitle(), 2.3);
        TitleAmount result = new TitleAmount(Movie.F004.getTitle(), 2.3);
        assertEquals(expected, result);
    }

    @Test
    void testNotEqualsDiffInTitle() {
        TitleAmount expected = new TitleAmount(Movie.F004.getTitle(), 2.3);
        TitleAmount result = new TitleAmount(Movie.F002.getTitle(), 2.3);
        assertNotEquals(expected, result);
    }

    @Test
    void testNotEqualsDiffInPoints() {
        TitleAmount expected = new TitleAmount(Movie.F004.getTitle(), 2.3);
        TitleAmount result = new TitleAmount(Movie.F004.getTitle(), 2.0);
        assertNotEquals(expected, result);
    }
}