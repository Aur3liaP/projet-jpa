package fr.diginamic.service;

import fr.diginamic.dao.FilmDao;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.entities.*;
import fr.diginamic.mapper.FilmMapper;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class FilmService {
    private final FilmDao filmDao;
    private final PaysService paysService;
    private final LieuService lieuService;
    private final GenreService genreService;

    /**
     * Service dédié à la gestion des entités Films.
     */
    public FilmService(EntityManager em) {
        this.filmDao = new FilmDao(em);
        this.paysService = new PaysService(em);
        this.lieuService = new LieuService(em);
        this.genreService = new GenreService(em);
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
        if (film.getGenres() != null) {
            List<Genre> genres = film.getGenres().stream()
                    .map(genreService::getOrCreateGenre)
                    .collect(Collectors.toList());
            film.setGenres(genres);
        }

        if (!filmDao.existsById(film.getIdImdb())) {
            filmDao.insert(film);
        }
    }
}
