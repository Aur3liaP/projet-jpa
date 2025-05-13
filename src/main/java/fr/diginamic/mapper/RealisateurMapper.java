package fr.diginamic.mapper;

import fr.diginamic.dto.RealisateurDto;
import fr.diginamic.entities.Realisateur;
import fr.diginamic.util.NaissanceUtils;

/**
 * Mapper pour convertir des DTOs de réalisateur en entités de réalisateur.
 * Cette classe fournit des méthodes pour transformer un {@link RealisateurDto} en une entité {@link Realisateur}.
 */
public class RealisateurMapper {
    /**
     * Convertit un DTO de réalisateur en une entité de réalisateur.
     *
     * @param dto Le DTO de réalisateur à convertir.
     * @return L'entité de réalisateur convertie, ou null si le DTO est null.
     */
    public static Realisateur toEntity(RealisateurDto dto) {
        if (dto == null) return null;

        Realisateur realisateur = new Realisateur();
        realisateur.setIdImdb(dto.getId());
        realisateur.setIdentite(dto.getIdentite());
        realisateur.setUrl(dto.getUrl());

        NaissanceUtils.setNaissanceInfo(realisateur, dto.getNaissance());

        return realisateur;
    }
}
