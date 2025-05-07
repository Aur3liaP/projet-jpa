package fr.diginamic.mapper;

import fr.diginamic.dto.PaysDto;
import fr.diginamic.entities.Pays;

public class PaysMapper {
    public static Pays toEntity(PaysDto dto) {
        if (dto == null) return null;

        Pays pays = new Pays();
        pays.setNom(dto.getNom());
        pays.setUrl(dto.getUrl());
        return pays;
    }
}
