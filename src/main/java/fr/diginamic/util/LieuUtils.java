package fr.diginamic.util;


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
        String[] lieuInfo = new String[4]; // [libelle, ville, état/région, pays]

        if (lieuStr == null || lieuStr.isBlank()) {
            return lieuInfo;
        }

        String[] parts = lieuStr.split(",");

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        switch (parts.length) {
            case 4:
                lieuInfo[0] = parts[0]; // libellé
                lieuInfo[1] = parts[1]; // ville
                lieuInfo[2] = parts[2]; // état/région
                lieuInfo[3] = parts[3]; // pays
                break;
            case 3:
                lieuInfo[0] = null;
                lieuInfo[1] = parts[0]; // ville
                lieuInfo[2] = parts[1]; // état/région
                lieuInfo[3] = parts[2]; // pays
                break;
            case 2:
                lieuInfo[0] = null;
                lieuInfo[1] = parts[0]; // ville
                lieuInfo[2] = null;
                lieuInfo[3] = parts[1]; // pays
                break;
            case 1:
                lieuInfo[0] = null;
                lieuInfo[1] = null;
                lieuInfo[2] = null;
                lieuInfo[3] = parts[0]; // pays
                break;
        }

        return lieuInfo;
    }

}
