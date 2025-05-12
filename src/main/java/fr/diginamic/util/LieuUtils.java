package fr.diginamic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilitaire pour le traitement des chaînes de caractères représentant des lieux.
 * Cette classe fournit des méthodes pour extraire les informations de lieu à partir de chaînes de caractères.
 */
public class LieuUtils {

    /**
     * Parse une chaîne de caractères représentant un lieu et extrait les informations de ville, état/région et pays.
     *
     * @param lieuStr La chaîne de caractères représentant le lieu à parser.
     * @return Un tableau de chaînes de caractères contenant les informations de lieu : [ville, état/région, pays].
     */
    public static String[] parseLieu(String lieuStr) {
        String[] lieuInfo = new String[3]; // ville, etatRegion, pays
        String regex = "^(?:([^,]+)(?:, )?)?(?:([^,]+)(?:, )?)?([^,]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(lieuStr.trim());

        if (matcher.find()) {
            // Si la chaîne contient deux virgules, alors c'est ville, état/région, pays
            if (lieuStr.split(",").length == 3) {
                lieuInfo[0] = matcher.group(1).trim(); // Ville
                lieuInfo[1] = matcher.group(2).trim(); // État/Région
                lieuInfo[2] = matcher.group(3).trim(); // Pays
            }
            // Si la chaîne contient une virgule, alors c'est ville, pays
            else if (lieuStr.split(",").length == 2) {
                lieuInfo[0] = matcher.group(1).trim(); // Ville
                lieuInfo[1] = null; // Pas d'État/Région
                lieuInfo[2] = matcher.group(2).trim(); // Pays
            }
            // Si la chaîne ne contient pas de virgule, alors c'est juste le pays
            else {
                lieuInfo[0] = null; // Pas de Ville
                lieuInfo[1] = null; // Pas d'État/Région
                lieuInfo[2] = matcher.group(3).trim(); // Pays
            }
        }

        return lieuInfo;
    }
}
