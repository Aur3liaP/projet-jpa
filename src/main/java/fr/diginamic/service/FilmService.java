package fr.diginamic.service;

import fr.diginamic.dao.FilmDao;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Lieu;
import fr.diginamic.entities.Pays;
import fr.diginamic.mapper.FilmMapper;
import jakarta.persistence.EntityManager;

public class FilmService {
    private final FilmDao filmDao;
    private final PaysService paysService;
    private final LieuService lieuService;

    /**
     * Service dédié à la gestion des entités Films.
     */
    public FilmService(EntityManager em) {
        this.filmDao = new FilmDao(em);
        this.paysService = new PaysService(em);
        this.lieuService = new LieuService(em);
    }

    /**
     * Récupère un pays existant par son nom ou l'insère s'il n'existe pas encore.
     *
     * @param filmDto L'objet Film à vérifier ou insérer
     */
    public void traiterFilm(FilmDto filmDto) {
        Film film = FilmMapper.toFilm(filmDto);

        if (film.getPays() != null) {
            Pays pays = paysService.getOrCreatePays(film.getPays());
            film.setPays(pays);
        }
        if (film.getLieuTournage() != null) {
            Lieu lieu = lieuService.getOrCreateLieu(film.getLieuTournage());
            film.setLieuTournage(lieu);
        }


        if (!filmDao.existsById(film.getIdImdb())) {
            filmDao.insert(film);
        }
    }
}
