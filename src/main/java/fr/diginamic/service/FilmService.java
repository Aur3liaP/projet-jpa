package fr.diginamic.service;

import fr.diginamic.dao.FilmDao;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.RoleDto;
import fr.diginamic.entities.*;
import fr.diginamic.mapper.ActeurMapper;
import fr.diginamic.mapper.FilmMapper;
import jakarta.persistence.EntityManager;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service dédié à la gestion des entités Film, à partir des données reçues sous forme de FilmDto.
 * Il centralise la transformation et la persistance des entités liées : pays, lieu de tournage, genres,
 * réalisateurs, acteurs et rôles.
 */
public class FilmService {
    /** Logger pour la journalisation des événements et erreurs */
    private static final Logger logger = LoggerFactory.getLogger(FilmService.class);

    /** DAO pour les opérations de persistance des films */
    private final FilmDao filmDao;

    /** Services pour la gestion des différentes entités associées aux films */
    private final PaysService paysService;
    private final LieuService lieuService;
    private final GenreService genreService;
    private final RealisateurService realisateurService;
    private final ActeurService acteurService;
    private final RoleService roleService;

    private final EntityManager em;

    /**
     * Constructeur du service de film.
     *
     * @param em EntityManager pour la gestion des persistances
     */
    public FilmService(EntityManager em) {
        this.filmDao = new FilmDao(em);
        this.paysService = new PaysService(em);
        this.lieuService = new LieuService(em);
        this.genreService = new GenreService(em);
        this.realisateurService = new RealisateurService(em);
        this.acteurService = new ActeurService(em);
        this.roleService = new RoleService(em);
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }

    /**
     * Traite un FilmDto pour transformer et persister ses entités associées :
     * pays, lieu de tournage, genres, réalisateurs, acteurs et rôles.
     *
     * @param filmDto Le DTO à convertir et insérer
     */
    public void traiterFilm(FilmDto filmDto) {
        if (filmDto == null) {
            logger.warn("Tentative de traitement d'un FilmDto null");
            return;
        }
        try{
            logger.debug("Traitement du film en cours...");
            // Conversion du DTO en entité
            Film film = FilmMapper.toFilm(filmDto);

            // Traitement des dépendances
            ajouterPays(film);
            ajouterLieuTournage(film);
            ajouterGenres(film);
            ajouterRealisateurs(film);


            Set<Acteur> tousLesActeurs = traiterActeursPrincipaux(filmDto);
            tousLesActeurs.addAll(traiterRoles(film, filmDto));
            film.setActeurs(new ArrayList<>(tousLesActeurs));

            persisterFilm(film);
            logger.info("Traitement terminé avec succès");

        } catch (Exception e) {
            logger.error("Erreur lors du traitement du film", e);
        }
    }

    /**
     * Ajoute le pays au film en le persistant si nécessaire.
     *
     * @param film Le film à traiter
     */
    private void ajouterPays(Film film) {
        if (film.getPays() != null) {
            Pays pays = paysService.getOrCreatePays(film.getPays());
            film.setPays(pays);
        }
    }

    /**
     * Ajoute le lieu de tournage au film en le persistant si nécessaire.
     *
     * @param film Le film à traiter
     */
    private void ajouterLieuTournage(Film film) {
        if (film.getLieuTournage() != null) {
            Lieu lieu = lieuService.getOrCreateLieu(film.getLieuTournage());
            film.setLieuTournage(lieu);
        }
    }

    /**
     * Ajoute les genres au film en les persistant si nécessaire.
     *
     * @param film Le film à traiter
     */
    private void ajouterGenres(Film film) {
        if (film.getGenres() != null) {
            List<Genre> genres = film.getGenres().stream()
                    .map(genreService::getOrCreateGenre)
                    .collect(Collectors.toList());
            film.setGenres(genres);
        }
    }

    /**
     * Ajoute les réalisateurs au film en les persistant si nécessaire.
     *
     * @param film Le film à traiter
     */
    private void ajouterRealisateurs(Film film) {
        if (film.getRealisateurs() != null) {
            List<Realisateur> realisateurs = film.getRealisateurs().stream()
                    .map(realisateurService::getOrCreateRealisateur)
                    .collect(Collectors.toList());
            film.setRealisateurs(realisateurs);
        }
    }

    /**
     * Traite les acteurs principaux du film.
     *
     * @param filmDto Le DTO du film
     * @return Un ensemble d'acteurs principaux
     */
    private Set<Acteur> traiterActeursPrincipaux(FilmDto filmDto) {
        Set<Acteur> acteursPrincipaux = new HashSet<>();

        if (filmDto.getActeursPrincipaux() != null) {
            acteursPrincipaux = filmDto.getActeursPrincipaux().stream()
                    .map(ActeurMapper::toEntity)
                    .map(acteurService::getOrCreateActeur)
                    .collect(Collectors.toSet());
        }

        return acteursPrincipaux;
    }

    /**
     * Traite les rôles du film.
     *
     * @param film Le film en cours de traitement
     * @param filmDto Le DTO du film
     * @return Un ensemble d'acteurs ayant des rôles dans le film
     */
    private Set<Acteur> traiterRoles(Film film, FilmDto filmDto) {
        Set<Acteur> acteursRoles = new HashSet<>();

        if (filmDto.getRoles() != null) {
            List<Role> roles = new ArrayList<>();

            for (RoleDto dto : filmDto.getRoles()) {
                // Persister l'acteur avant de créer le rôle
                Acteur acteur = acteurService.getOrCreateActeur(
                        ActeurMapper.toEntity(dto.getActeur()));

                Role role = new Role();
                role.setFilm(film);
                role.setActeur(acteur);
                role.setPersonnage(dto.getPersonnage());

                roles.add(roleService.getOrCreateRole(role));
                acteursRoles.add(acteur);
            }

            film.setRoles(roles);
        }

        return acteursRoles;
    }

    /**
     * Persiste le film s'il n'existe pas déjà.
     *
     * @param film Le film à persister
     */
    private void persisterFilm(Film film) {
        if (!filmDao.existsById(film.getIdImdb())) {
            try {
                filmDao.insert(film);
                logger.info("Film inséré avec succès : {}", film.getTitre());
            } catch (Exception e) {
                logger.error("Erreur lors de l'insertion du film : {}", film.getTitre(), e);
            }
        } else {
            logger.info("Film déjà existant : {}", film.getTitre());
        }
    }
}