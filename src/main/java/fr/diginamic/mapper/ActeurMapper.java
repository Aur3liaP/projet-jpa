package fr.diginamic.mapper;

import fr.diginamic.dto.ActeurDto;
import fr.diginamic.entities.Acteur;
import fr.diginamic.util.NaissanceUtils;
import fr.diginamic.util.TailleUtils;

public class ActeurMapper {

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
