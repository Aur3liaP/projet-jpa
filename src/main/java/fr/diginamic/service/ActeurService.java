package fr.diginamic.service;

import fr.diginamic.dao.ActeurDao;
import fr.diginamic.entities.Acteur;
import jakarta.persistence.EntityManager;

/**
 * Service dédié à la gestion des entités Acteur.
 */
public class ActeurService {

    private final ActeurDao acteurDao;
    private final LieuService lieuService;

    public ActeurService(EntityManager em) {
        this.acteurDao = new ActeurDao(em);
        this.lieuService = new LieuService(em);
    }

    /**
     * Récupère un acteur de film existant par son id ou l'insère s'il n'existe pas encore.
     *
     * @param acteur L'objet Acteur à vérifier ou insérer
     * @return L'entité Acteur persisté (existant ou nouvellement inséré)
     */
    public Acteur getOrCreateActeur(Acteur acteur) {
        Acteur existant = acteurDao.findById(acteur.getIdImdb());
        if (existant != null) {
            return existant;
        } else {
            if (acteur.getLieuNaissance() != null) {
                acteur.setLieuNaissance(lieuService.getOrCreateLieu(acteur.getLieuNaissance()));
            }
            acteurDao.insert(acteur);
            return acteur;
        }
    }
}