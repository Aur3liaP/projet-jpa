package fr.diginamic.mapper;

import fr.diginamic.dto.NaissanceDto;
import fr.diginamic.dto.PaysDto;
import fr.diginamic.dto.RealisateurDto;
import fr.diginamic.entities.Lieu;
import fr.diginamic.entities.Realisateur;
import fr.diginamic.util.DateUtils;
import fr.diginamic.util.LieuUtils;

public class RealisateurMapper {
    public static Realisateur toEntity(RealisateurDto dto) {
        if (dto == null) return null;

        Realisateur realisateur = new Realisateur();
        realisateur.setIdImdb(dto.getId());
        realisateur.setIdentite(dto.getIdentite());
        realisateur.setUrl(dto.getUrl());

        // Gestion de la naissance
        NaissanceDto naissanceDto = dto.getNaissance();
        if (naissanceDto != null) {
            String dateNaissanceStr = naissanceDto.getDateNaissance();
            realisateur.setDateNaissance(DateUtils.parseDate(dateNaissanceStr));

            String lieuNaissanceStr = naissanceDto.getLieuNaissance();
            String[] lieuInfo = LieuUtils.parseLieu(lieuNaissanceStr);

            Lieu lieuNaissance = new Lieu();
            lieuNaissance.setVille(lieuInfo[0]);
            lieuNaissance.setEtatRegion(lieuInfo[1]);

            PaysDto paysDto = new PaysDto();
            paysDto.setNom(lieuInfo[2]);

            lieuNaissance.setPays(PaysMapper.toEntity(paysDto));
            realisateur.setLieuNaissance(lieuNaissance);
        }


        return realisateur;
    }
}
