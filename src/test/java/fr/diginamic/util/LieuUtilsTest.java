package fr.diginamic.util;

import fr.diginamic.dto.LieuDto;
import fr.diginamic.dto.PaysDto;
import fr.diginamic.entities.Lieu;
import fr.diginamic.entities.Pays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LieuUtilsTest {

    /**
     * Teste la bonne conversion des lieux avec le pays de l'adresse, injecté dans la table Pays
     */
    @Test
    void convertPaysDtoToEntity() {
        PaysDto paysDto = new PaysDto();
        paysDto.setNom("United States");
        paysDto.setUrl("/search/title/?country_of_origin=US");

        Pays pays = LieuUtils.convertPaysDtoToEntity(paysDto);
        assertEquals("United States", pays.getNom());
        assertEquals("/search/title/?country_of_origin=US", pays.getUrl());

    }

    @Test
    void convertLieuDtoToEntity() {
        LieuDto lieuDto = new LieuDto();
        lieuDto.setVille("Woodstock");
        lieuDto.setEtatRegion("Vermont");
        lieuDto.setPays("USA");

        Lieu lieu = LieuUtils.convertLieuDtoToEntity(lieuDto);
        assertEquals("Woodstock", lieu.getVille());
        assertEquals("Vermont", lieu.getEtatRegion());
        assertEquals("USA", lieu.getPays().getNom());
    }
}