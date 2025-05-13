package fr.diginamic.mapper;

import fr.diginamic.dto.ActeurDto;
import fr.diginamic.entities.Acteur;
import fr.diginamic.util.NaissanceUtils;
import fr.diginamic.util.TailleUtils;

/**
 * Mapper pour convertir des DTOs d'un acteur en entités d'un acteur.
 * Cette classe fournit des méthodes pour transformer un ActeurDto en une entité Acteur.
 */
public class ActeurMapper {

    /**
     * Convertit un DTO d'un acteur en une entité d'un acteur.
     *
     * @param dto Le DTO d'un acteur à convertir.
     * @return L'entité d'un acteur convertie, ou null si le DTO est null.
     */
    public static Acteur toEntity(ActeurDto dto) {
        if (dto == null) return null;

        Acteur acteur = new Acteur();
        acteur.setIdImdb(dto.getId());
        acteur.setIdentite(dto.getIdentite());
        acteur.setUrl(dto.getUrl());
        acteur.setTaille(TailleUtils.parseTaille(dto.getTaille()));

        NaissanceUtils.setNaissanceInfo(acteur, dto.getNaissance());

        return acteur;
    }
}
