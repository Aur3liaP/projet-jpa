package fr.diginamic.service;

import fr.diginamic.dao.FilmDao;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Pays;
import fr.diginamic.mapper.FilmMapper;
import jakarta.persistence.EntityManager;

public class FilmService {
    private final FilmDao filmDao;
    private final PaysService paysService;

    /**
     * Service dédié à la gestion des entités Films.
     */
    public FilmService(EntityManager em) {
        this.filmDao = new FilmDao(em);
        this.paysService = new PaysService(em);
    }

    /**
     * Récupère un pays existant par son nom ou l'insère s'il n'existe pas encore.
     *
     * @param filmDto L'objet Film à vérifier ou insérer
     * @return L'entité Film persistée (existant ou nouvellement insérée)
     */
    public void traiterFilm(FilmDto filmDto) {
        Film film = FilmMapper.toFilm(filmDto);

        if (film.getPays() != null) {
            Pays pays = paysService.getOrCreate(film.getPays());
            film.setPays(pays);
        }

        if (!filmDao.existsById(film.getIdImdb())) {
            filmDao.insert(film);
        }
    }
}
