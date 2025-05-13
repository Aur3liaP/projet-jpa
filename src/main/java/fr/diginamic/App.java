package fr.diginamic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.service.FilmService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

/**
 * Classe principale de l'application permettant de charger et traiter les données de films.
 * Gère le chargement des films depuis un fichier JSON et leur persistance en base de données.
 */
public class App {
    /** Logger pour la journalisation des événements et erreurs */
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /** Point d'entrée principal de l'application. */
    public static void main(String[] args) {
        // Utilisation du logger
        logger.debug("Application démarrée");
        logger.info("Information de l'application");
        logger.error("Une erreur s'est produite", new Exception("Exemple d'exception"));


        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            long start = System.currentTimeMillis();

            emf = Persistence.createEntityManagerFactory("imdb");
            em = emf.createEntityManager();

            List<FilmDto> filmDtos = chargerFilms();

            FilmService filmService = new FilmService(em);

            traiterFilms(filmDtos, filmService);

            long end = System.currentTimeMillis();
            logger.info("Traitement des films terminé avec succès");
            System.out.println("Durée : " + (end - start) + " ms");

        } catch (Exception e) {
            logger.error("Erreur lors du traitement des films", e);

            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {

            fermerRessources(em, emf);
        }
    }

    /**
     * Charge les films depuis le fichier JSON.
     *
     * @return Liste des films chargés
     * @throws Exception en cas d'erreur de lecture
     */
    private static List<FilmDto> chargerFilms() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream input = App.class.getResourceAsStream("/data/films.json")) {
            if (input == null) {
                throw new IllegalStateException("Fichier films.json introuvable");
            }
            return mapper.readValue(input, new TypeReference<>() {});
        }
    }

    /**
     * Traite la liste des films.
     *
     * @param filmDtos Liste des films à traiter
     * @param filmService Service de traitement des films
     */
    private static void traiterFilms(List<FilmDto> filmDtos, FilmService filmService) {
        EntityManager em = filmService.getEntityManager();
        int filmsTraites = 0;
        int filmsEnEchec = 0;

        for (FilmDto filmDto : filmDtos) {
            try {
                em.getTransaction().begin();

                filmService.traiterFilm(filmDto);

                em.getTransaction().commit();
                filmsTraites++;
                logger.info("Film '{}' traité avec succès", filmDto.getTitre());

            } catch (Exception e) {
                filmsEnEchec++;
                logger.error("Erreur lors du traitement du film : {}", filmDto.getTitre(), e);
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.clear();
            }
        }

        logger.info("Résumé : {} films traités, {} films en échec", filmsTraites, filmsEnEchec);
    }

    /**
     * Ferme les ressources de persistence.
     *
     * @param em EntityManager à fermer
     * @param emf EntityManagerFactory à fermer
     */
    private static void fermerRessources(EntityManager em, EntityManagerFactory emf) {
        try {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la fermeture des ressources", e);
        }
    }
}