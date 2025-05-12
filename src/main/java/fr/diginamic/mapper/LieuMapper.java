package fr.diginamic.mapper;

import fr.diginamic.dto.LieuDto;
import fr.diginamic.dto.PaysDto;
import fr.diginamic.entities.Lieu;

public class LieuMapper {
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
