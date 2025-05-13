package fr.diginamic.util;

import fr.diginamic.dto.NaissanceDto;
import fr.diginamic.entities.Personne;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe NaissanceUtils.
 * Ces tests vérifient le fonctionnement de la méthode setNaissanceInfo
 * dans différentes situations.
 */
public class NaissanceUtilsTest {

    /**
     * Teste la méthode setNaissanceInfo avec des données complètes de naissance.
     * Ce test vérifie que les informations de naissance sont correctement définies
     * sur l'objet Personne.
     */
    @Test
    public void testSetNaissanceInfoAvecDonneesCompletes() {
        Personne personne = new Personne();
        NaissanceDto naissanceDto = new NaissanceDto();
        naissanceDto.setDateNaissance("01/01/1990");
        naissanceDto.setLieuNaissance("Bronx, New-York City, New York, USA");

        NaissanceUtils.setNaissanceInfo(personne, naissanceDto);

        assertNotNull(personne.getLieuNaissance());
        assertEquals("Bronx", personne.getLieuNaissance().getLibelle());
        assertEquals("New-York City", personne.getLieuNaissance().getVille());
        assertEquals("New York", personne.getLieuNaissance().getEtatRegion());
        assertNotNull(personne.getLieuNaissance().getPays());
    }

    /**
     * Teste la méthode setNaissanceInfo avec des données de naissance nulles.
     * Ce test vérifie que la méthode gère correctement le cas où naissanceDto est null.
     */
    @Test
    public void testSetNaissanceInfoAvecDonneesNulles() {
        Personne personne = new Personne();

        NaissanceUtils.setNaissanceInfo(personne, null);

        assertNull(personne.getDateNaissance());
        assertNull(personne.getLieuNaissance());
    }
}