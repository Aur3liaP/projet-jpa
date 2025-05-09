package fr.diginamic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.service.FilmService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.InputStream;
import java.util.List;

/**
 * Permet de lancer l'application
 */
// TODO verifier la BDD
public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("imdb");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            /**
             * Lire le JSON depuis resources/data/films.json
             */
            ObjectMapper mapper = new ObjectMapper();
            InputStream input = App.class.getResourceAsStream("/data/films.json");
            List<FilmDto> filmDtos = mapper.readValue(input, new TypeReference<List<FilmDto>>() {});

            /**
             * Initialiser Services
             */
            FilmService filmService = new FilmService(em);


            /**
             * Traitement des films
             */
            for (FilmDto filmDto : filmDtos) {
                try {
                    filmService.traiterFilm(filmDto);

                } catch (Exception e) {
                    // TODO voir pour le rapport de logs
                    System.err.println("Erreur pour le film : " + filmDto.getTitre());
                    e.printStackTrace();
                }
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
