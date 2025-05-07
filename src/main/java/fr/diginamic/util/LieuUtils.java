package fr.diginamic.util;

import fr.diginamic.dto.LieuDto;
import fr.diginamic.dto.PaysDto;
import fr.diginamic.entities.Pays;
import fr.diginamic.entities.Lieu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LieuUtils {

    public static String[] parseLieu(String lieuStr) {
        String[] lieuInfo = new String[3]; // ville, etatRegion, pays
        String regex = "^(?:([^,]+)(?:, )?)?(?:([^,]+)(?:, )?)?([^,]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(lieuStr);

        if (matcher.find()) {
            lieuInfo[0] = matcher.group(1) != null ? matcher.group(1).trim() : null; // Ville
            lieuInfo[1] = matcher.group(2) != null ? matcher.group(2).trim() : null; // État/Région
            lieuInfo[2] = matcher.group(3) != null ? matcher.group(3).trim() : null; // Pays
        }

        return lieuInfo;
    }

    /**
     * Convertis les pays et lieux des DTO vers les entités JPA
     */
    public static Pays convertPaysDtoToEntity(PaysDto paysDto) {
        if (paysDto == null) {
            return null;
        }
        Pays pays = new Pays();
        pays.setNom(paysDto.getNom());
        pays.setUrl(paysDto.getUrl());
        return pays;
    }

    public static Lieu convertLieuDtoToEntity(LieuDto lieuDto) {
        Lieu lieu = new Lieu();
        lieu.setVille(lieuDto.getVille());
        lieu.setEtatRegion(lieuDto.getEtatRegion());

        PaysDto paysDto = new PaysDto();
        paysDto.setNom(lieuDto.getPays());
        lieu.setPays(convertPaysDtoToEntity(paysDto));
        return lieu;
    }
    // TODO A corriger pour differencier lieuNaissance et lieuTournage
}
