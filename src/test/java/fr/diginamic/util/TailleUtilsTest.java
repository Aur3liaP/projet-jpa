package fr.diginamic.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe TailleUtils.
 * Ces tests vérifient le bon fonctionnement de la méthode parseTaille
 * dans différentes situations.
 */
public class TailleUtilsTest {

    /**
     * Teste le parseur de taille avec une entrée standard avec unité.
     * Vérifie que la valeur numérique est correctement extraite en ignorant l'unité.
     */
    @Test
    public void testParseTailleAvecUnite() {
        String tailleStr = "1.85 m";

        Double resultat = TailleUtils.parseTaille(tailleStr);

        assertNotNull(resultat);
        assertEquals(1.85, resultat, 0.001);
    }

    /**
     * Teste le parseur de taille avec une notation utilisant une virgule
     * au lieu d'un point décimal.
     * Vérifie que la conversion est correctement effectuée.
     */
    @Test
    public void testParseTailleAvecVirgule() {
        String tailleStr = "1,75 mètres";

        Double resultat = TailleUtils.parseTaille(tailleStr);

        assertNotNull(resultat);
        assertEquals(1.75,resultat, 0.001);
    }

    /**
     * Teste le parseur de taille avec une entrée vide.
     * Vérifie que la méthode renvoie null.
     */
    @Test
    public void testParseTailleAvecEntreeVide() {
        String tailleStr = "   ";

        Double resultat = TailleUtils.parseTaille(tailleStr);

        assertNull(resultat);
    }
}