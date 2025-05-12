package fr.diginamic.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste le bon parsage des dates reçues du Json
 */
class DateUtilsTest {
    @Test
    public void testParseDate() {
        String dateStr = "February 8 1946";
        LocalDate expectedDate = LocalDate.of(1946, 2, 8);
        LocalDate parsedDate = DateUtils.parseDate(dateStr);
        assertEquals(expectedDate, parsedDate);
    }

    @Test
    public void testParseDateWithSpace() {
        String dateStr = "February 8 1946 ";
        LocalDate expectedDate = LocalDate.of(1946, 2, 8);
        LocalDate parsedDate = DateUtils.parseDate(dateStr);
        assertEquals(expectedDate, parsedDate);
    }
}