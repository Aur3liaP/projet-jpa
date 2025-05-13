package fr.diginamic.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe LieuUtils.
 * Ces tests vérifient le bon fonctionnement de la méthode parseLieu
 * dans différentes situations.
 */
public class LieuUtilsTest {

    /**
     * Teste le parseur de lieu avec une entrée complète contenant libellé, ville, région et pays.
     * Vérifie que les quatre éléments sont correctement extraits.
     */
    @Test
    public void testParseLieuAvecQuatreElements() {
        String lieuStr = "Bronx, New-York City, New York, USA ";

        String[] resultat = LieuUtils.parseLieu(lieuStr);

        assertEquals("Bronx", resultat[0]);
        assertEquals("New-York City", resultat[1]);
        assertEquals("New York", resultat[2]);
        assertEquals("USA", resultat[3]);
    }

    /**
     * Teste le parseur de lieu avec une entrée contenant ville, région et pays (sans libellé).
     * Vérifie que les trois éléments sont correctement extraits et que le libellé est null.
     */
    @Test
    public void testParseLieuAvecTroisElements() {
        String lieuStr = "New York, New York, États-Unis";

        String[] resultat = LieuUtils.parseLieu(lieuStr);

        assertNull(resultat[0]);
        assertEquals("New York", resultat[1]);
        assertEquals("New York", resultat[2]);
        assertEquals("États-Unis", resultat[3]);
    }

}