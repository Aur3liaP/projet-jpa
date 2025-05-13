package fr.diginamic.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe RatingUtils.
 * Ces tests vérifient le bon fonctionnement de la méthode parseRating
 * dans différentes situations.
 */
public class RatingUtilsTest {

    /**
     * Teste le parseur de note avec une entrée standard.
     * Vérifie que la valeur numérique est correctement extraite.
     */
    @Test
    public void testParseRatingAvecEntreeStandard() {
        String ratingStr = "4.5";

        Float resultat = RatingUtils.parseRating(ratingStr);

        assertNotNull(resultat);
        assertEquals(4.5f, resultat, 0.001);
    }

    /**
     * Teste le parseur de note avec une notation utilisant une virgule
     * au lieu d'un point décimal.
     * Vérifie que la conversion est correctement effectuée.
     */
    @Test
    public void testParseRatingAvecVirgule() {
        String ratingStr = "3,8";

        Float resultat = RatingUtils.parseRating(ratingStr);

        assertNotNull(resultat);
        assertEquals( 3.8f, resultat, 0.001);
    }

    /**
     * Teste le parseur de note avec une entrée invalide (non numérique).
     * Vérifie que la méthode renvoie null.
     */
    @Test
    public void testParseRatingAvecEntreeInvalide() {
        String ratingStr = "excellent";

        Float resultat = RatingUtils.parseRating(ratingStr);

        assertNull(resultat);
    }
}