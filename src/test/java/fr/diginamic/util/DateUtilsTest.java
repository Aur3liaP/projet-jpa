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
        String dateStr = "May 7 1940";
        LocalDate expectedDate = LocalDate.of(1940, 5, 7);
        LocalDate parsedDate = DateUtils.parseDate(dateStr);
        assertEquals(expectedDate, parsedDate);
    }
}