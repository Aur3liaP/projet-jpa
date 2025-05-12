package fr.diginamic.service;


import fr.diginamic.dao.LieuDao;
import fr.diginamic.entities.Lieu;
import fr.diginamic.entities.Pays;
import jakarta.persistence.EntityManager;


/**
 * Service dédié à la gestion des entités Lieu.
 */
public class LieuService {
    private final LieuDao lieuDao;
    private final PaysService paysService;

    public LieuService(EntityManager em) {
        this.lieuDao = new LieuDao(em);
        this.paysService = new PaysService(em);
    }

    /**
     * Récupère un lieu de Tournage existant par son nom ou l'insère s'il n'existe pas encore.
     *
     * @param lieu L'objet Lieu à vérifier ou insérer
     * @return L'entité Lieu persisté (existant ou nouvellement inséré)
     */
    public Lieu getOrCreateLieu(Lieu lieu) {
        Pays paysPersisted = paysService.getOrCreatePays(lieu.getPays());
        lieu.setPays(paysPersisted);

        Lieu existant = lieuDao.findByDetails(lieu.getVille(), lieu.getEtatRegion(), lieu.getPays());

        if (existant != null) {
            return existant;
        } else {
            lieuDao.insert(lieu);
            return lieu;
        }

    }

}
