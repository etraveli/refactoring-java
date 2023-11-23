package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TitleAmountTest {

    @Test
    public void givenTitleAmount_whenCallingToString_thenReturnEasyReadString() {
        String sb = "\t" + Movie.F004.getTitle() + "\t" + "2.3" + "\n";

        TitleAmount result = new TitleAmount(Movie.F004.getTitle(), 2.3);
        assertEquals(sb, result.toString());
    }

    @Test
    public void givenTitleAmountsDoNotDiffer_whenComparing_thenReturnEqual() {
        TitleAmount expected = new TitleAmount(Movie.F004.getTitle(), 2.3);
        TitleAmount result = new TitleAmount(Movie.F004.getTitle(), 2.3);
        assertEquals(expected, result);
    }

    @Test
    public void givenTitleDiffer_whenComparing_thenReturnNotEqual() {
        TitleAmount expected = new TitleAmount(Movie.F004.getTitle(), 2.3);
        TitleAmount result = new TitleAmount(Movie.F002.getTitle(), 2.3);
        assertNotEquals(expected, result);
    }

    @Test
    public void givenAmountDiffer_whenComparing_thenReturnNotEqual() {
        TitleAmount expected = new TitleAmount(Movie.F004.getTitle(), 2.3);
        TitleAmount result = new TitleAmount(Movie.F004.getTitle(), 2.0);
        assertNotEquals(expected, result);
    }
}