package fr.diginamic.util;

import fr.diginamic.dto.NaissanceDto;
import fr.diginamic.dto.PaysDto;
import fr.diginamic.entities.Lieu;
import fr.diginamic.entities.Personne;
import fr.diginamic.mapper.PaysMapper;

public class NaissanceUtils {

    public static void setNaissanceInfo(Personne personne, NaissanceDto naissanceDto) {
        if (naissanceDto != null) {
            personne.setDateNaissance(DateUtils.parseDate(naissanceDto.getDateNaissance()));

            // Utilisation de LieuUtils pour extraire les informations de lieu
            String lieuNaissanceStr = naissanceDto.getLieuNaissance();
            String[] lieuInfo = LieuUtils.parseLieu(lieuNaissanceStr);

            Lieu lieuNaissance = new Lieu();
            lieuNaissance.setLibelle(lieuInfo[0]);
            lieuNaissance.setVille(lieuInfo[1]);
            lieuNaissance.setEtatRegion(lieuInfo[2]);

            // Création d'une instance de PaysDto et définition du nom du pays
            PaysDto paysDto = new PaysDto();
            paysDto.setNom(lieuInfo[3]);

            lieuNaissance.setPays(PaysMapper.toEntity(paysDto));
            personne.setLieuNaissance(lieuNaissance);
        }
    }
}
