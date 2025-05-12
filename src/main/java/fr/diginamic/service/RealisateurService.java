package fr.diginamic.service;

import fr.diginamic.dao.RealisateurDao;
import fr.diginamic.entities.Realisateur;
import jakarta.persistence.EntityManager;

/**
 * Service dédié à la gestion des entités Realisateur.
 */
public class RealisateurService {
    private final RealisateurDao realisateurDao;
    private final LieuService lieuService;

    public RealisateurService(EntityManager em) {
        this.realisateurDao = new RealisateurDao(em);
        this.lieuService = new LieuService(em);
    }

    /**
     * Récupère un réalisateur de film existant par son id Imdb ou l'insère s'il n'existe pas encore.
     *
     * @param realisateur L'objet Realisateur à vérifier ou insérer
     * @return L'entité Realisateur persisté (existant ou nouvellement inséré)
     */
    public Realisateur getOrCreateRealisateur(Realisateur realisateur) {
        Realisateur existant = realisateurDao.findById(realisateur.getIdImdb());
        if (existant != null) {
            return existant;
        } else {
            if (realisateur.getLieuNaissance() != null) {
                realisateur.setLieuNaissance(lieuService.getOrCreateLieu(realisateur.getLieuNaissance()));
            }
            realisateurDao.insert(realisateur);
            return realisateur;
        }
    }
}
