package fr.diginamic.util;

public class RatingUtils {
    /**
     * Extrait la note numérique depuis une chaîne contenant des unités (ex: "2.6").
     *
     * @param ratingStr la chaîne à parser
     * @return la note en float, ou null si la chaîne est invalide
     */
    public static Float parseRating(String ratingStr) {
        if (ratingStr == null || ratingStr.isBlank()) return null;

        String cleaned = ratingStr.replace(",", ".");

        try {
            return Float.parseFloat(cleaned);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
