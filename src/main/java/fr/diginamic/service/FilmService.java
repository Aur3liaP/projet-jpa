package fr.diginamic.service;

import fr.diginamic.dao.FilmDao;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.RoleDto;
import fr.diginamic.entities.*;
import fr.diginamic.mapper.ActeurMapper;
import fr.diginamic.mapper.FilmMapper;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FilmService {
    private final FilmDao filmDao;
    private final PaysService paysService;
    private final LieuService lieuService;
    private final GenreService genreService;
    private final RealisateurService realisateurService;
    private final ActeurService acteurService;
    private final RoleService roleService;

    /**
     * Service dédié à la gestion des entités Films.
     */
    public FilmService(EntityManager em) {
        this.filmDao = new FilmDao(em);
        this.paysService = new PaysService(em);
        this.lieuService = new LieuService(em);
        this.genreService = new GenreService(em);
        this.realisateurService = new RealisateurService(em);
        this.acteurService = new ActeurService(em);
        this.roleService = new RoleService(em);
    }

    /**
     * Traite un FilmDto pour transformer et persister ses entités associées :
     * pays, lieu de tournage, genres, réalisateurs, acteurs et rôles.
     *
     * @param filmDto filmDto Le DTO à convertir et insérer.
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
        if (film.getRealisateurs() != null) {
            List<Realisateur> realisateurs = film.getRealisateurs().stream()
                    .map(realisateurService::getOrCreateRealisateur)
                    .collect(Collectors.toList());
            film.setRealisateurs(realisateurs);
        }

        Set<Acteur> tousLesActeurs = new HashSet<>();

// Acteurs principaux
        if (filmDto.getActeursPrincipaux() != null) {
            List<Acteur> principaux = filmDto.getActeursPrincipaux().stream()
                    .map(ActeurMapper::toEntity)
                    .map(acteurService::getOrCreateActeur)
                    .toList();
            tousLesActeurs.addAll(principaux);
        }

// Rôles
        if (filmDto.getRoles() != null) {
            List<Role> roles = new ArrayList<>();

            for (RoleDto dto : filmDto.getRoles()) {
                // Très important : persister l'acteur avant de créer le rôle
                Acteur acteur = acteurService.getOrCreateActeur(
                        ActeurMapper.toEntity(dto.getActeur()));

                Role role = new Role();
                role.setFilm(film);
                role.setActeur(acteur);
                role.setPersonnage(dto.getPersonnage());

                roles.add(roleService.getOrCreateRole(role));
                tousLesActeurs.add(acteur);
            }

            film.setRoles(roles);
        }

        film.setActeurs(new ArrayList<>(tousLesActeurs));

        if (!filmDao.existsById(film.getIdImdb())) {
            filmDao.insert(film);
        }


    }

}
