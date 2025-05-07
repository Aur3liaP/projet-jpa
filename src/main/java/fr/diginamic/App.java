package fr.diginamic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.dao.FilmDao;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.entities.Film;
import fr.diginamic.mapper.FilmMapper;
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
             * Initialiser DAO
             */
            FilmDao filmDao = new FilmDao(em);

            /**
             * Traitement des films
             */
            for (FilmDto filmDto : filmDtos) {
                try {
                    Film film = FilmMapper.toFilm(filmDto);

                    /**
                     * Vérifie si le film est déjà présent
                     */
                    if (!filmDao.existsById(film.getIdImdb())) {
                        filmDao.insert(film);
                    }

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
