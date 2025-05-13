package fr.diginamic.util;

import fr.diginamic.dto.NaissanceDto;
import fr.diginamic.dto.PaysDto;
import fr.diginamic.entities.Lieu;
import fr.diginamic.entities.Personne;
import fr.diginamic.mapper.PaysMapper;

/**
 * Utilitaire pour gérer les informations de naissance d'une personne.
 * Cette classe fournit des méthodes pour configurer les informations de naissance
 * d'une instance de {@link Personne} à partir d'un DTO de naissance.
 */
public class NaissanceUtils {

    /**
     * Configure les informations de naissance d'une personne à partir d'un DTO de naissance.
     *
     * @param personne     L'instance de {@link Personne} à laquelle les informations de naissance seront appliquées.
     * @param naissanceDto Le DTO contenant les informations de naissance, y compris la date et le lieu de naissance.
     *
     * @see NaissanceDto
     * @see Personne
     * @see Lieu
     * @see PaysDto
     */
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
