package fr.diginamic.util;

public class TailleUtils {
    /**
     * Extrait la taille numérique depuis une chaîne contenant des unités (ex: "1.70 m").
     *
     * @param tailleStr la chaîne à parser
     * @return la taille en double, ou null si la chaîne est invalide
     */
    public static Double parseTaille(String tailleStr) {
        if (tailleStr == null || tailleStr.isBlank()) return null;

        String cleaned = tailleStr.replace(",", ".").replaceAll("[^\\d.]", "");

        try {
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
