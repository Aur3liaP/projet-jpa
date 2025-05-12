package fr.diginamic.mapper;

import fr.diginamic.dto.RealisateurDto;
import fr.diginamic.entities.Realisateur;
import fr.diginamic.util.NaissanceUtils;

public class RealisateurMapper {
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
