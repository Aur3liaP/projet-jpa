package fr.diginamic.mapper;

import fr.diginamic.dto.PaysDto;
import fr.diginamic.entities.Pays;

/**
 * Mapper pour convertir des DTOs de pays en entités de pays.
 * Cette classe fournit des méthodes pour transformer un {@link PaysDto} en une entité {@link Pays}.
 */
public class PaysMapper {
    /**
     * Convertit un DTO de pays en une entité de pays.
     *
     * @param dto Le DTO de pays à convertir.
     * @return L'entité de pays convertie, ou null si le DTO est null.
     */
    public static Pays toEntity(PaysDto dto) {
        if (dto == null) return null;

        Pays pays = new Pays();
        pays.setNom(dto.getNom());
        pays.setUrl(dto.getUrl());
        return pays;
    }
}
