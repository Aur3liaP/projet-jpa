package fr.diginamic.service;

import fr.diginamic.dao.GenreDao;
import fr.diginamic.entities.Genre;
import jakarta.persistence.EntityManager;

/**
 * Service dédié à la gestion des entités Genre.
 */
public class GenreService {
    private final GenreDao genreDao;

    public GenreService(EntityManager em) {
        this.genreDao = new GenreDao(em);
    }


    /**
     * Récupère un genre de film existant par son nom ou l'insère s'il n'existe pas encore.
     *
     * @param genre L'objet Genre à vérifier ou insérer
     * @return L'entité Genre persisté (existant ou nouvellement inséré)
     */
    public Genre getOrCreateGenre(Genre genre) {
        Genre existant = genreDao.findByNom(genre.getNom());

        if (existant != null) {
            return existant;
        } else {
            genreDao.insert(genre);
            return genre;
        }
    }
}
