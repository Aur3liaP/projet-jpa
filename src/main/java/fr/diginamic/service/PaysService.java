package fr.diginamic.service;

import fr.diginamic.dao.PaysDao;
import fr.diginamic.entities.Pays;
import jakarta.persistence.EntityManager;

/**
 * Service dédié à la gestion des entités Pays.
 */
public class PaysService {
    private final PaysDao paysDao;

    public PaysService(EntityManager em) {
        this.paysDao = new PaysDao(em);
    }

    /**
     * Récupère un pays existant par son nom ou l'insère s'il n'existe pas encore.
     *
     * @param pays L'objet Pays à vérifier ou insérer
     * @return L'entité Pays persistée (existant ou nouvellement insérée)
     */
    public Pays getOrCreate(Pays pays) {
        if (pays == null || pays.getNom() == null) return null;

        Pays existant = paysDao.findByNom(pays.getNom());
        if (existant != null) {
            return existant;
        } else {
            paysDao.insert(pays);
            return pays;
        }
    }
}
