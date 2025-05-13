package fr.diginamic.mapper;

import fr.diginamic.dto.LieuDto;
import fr.diginamic.dto.PaysDto;
import fr.diginamic.entities.Lieu;

/**
 * Mapper pour convertir des DTOs de lieu en entités de lieu.
 * Cette classe fournit des méthodes pour transformer un {@link LieuDto} en une entité {@link Lieu}.
 */
public class LieuMapper {
    /**
     * Convertit un DTO de lieu en une entité de lieu.
     *
     * @param dto Le DTO de lieu à convertir.
     * @return L'entité de lieu convertie, ou null si le DTO est null.
     */
    public static Lieu toEntity(LieuDto dto) {
        if (dto == null) return null;

        Lieu lieu = new Lieu();
        lieu.setVille(dto.getVille());
        lieu.setEtatRegion(dto.getEtatRegion());

        PaysDto paysDto = new PaysDto();
        paysDto.setNom(dto.getPays());
        lieu.setPays(PaysMapper.toEntity(paysDto));
        return lieu;

    }
}
