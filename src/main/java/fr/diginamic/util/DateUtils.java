package fr.diginamic.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Permet de traiter les différents cas de figure des dates et de les convertir dans un format parsé
 */
public class DateUtils {
    /**
     * Extrait la date depuis une chaîne contenant des dates de formats différents.
     *
     * @param dateStr la chaîne à parser
     * @return la date formatée et parsée
     */
    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }

        dateStr = dateStr.trim();

        if (Pattern.matches("^\\d{4}$", dateStr)) {
            int year = Integer.parseInt(dateStr);
            return LocalDate.of(year, 1, 1);
        }

        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("MMMM dd", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("MMMM d", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("M/d/yyyy", Locale.ENGLISH)
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                // Test format suivant
            }
        }
        throw new IllegalArgumentException("Impossible de parser la date: " + dateStr);
    }
}