package fr.diginamic.dao;

import fr.diginamic.entities.Film;
import jakarta.persistence.*;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO spécifique pour l'entité Film.
 */
/**
 * DAO spécifique pour l'entité Film.
 */
public class FilmDao implements BaseDao<Film> {

    private EntityManagerFactory emf;
    private EntityManager em;

    public FilmDao() {
        this.emf = Persistence.createEntityManagerFactory("imdb");
        this.em = emf.createEntityManager();
    }

    @Override
    public List<Film> extraire() throws SQLException {
        return em.createQuery("SELECT f FROM Film f", Film.class).getResultList();
    }

    @Override
    public void insert(Film film) {
        em.persist(film);
    }

    @Override
    public int update(Film film) throws SQLException {
        em.merge(film);
        return 1;
    }

    @Override
    public boolean delete(Film film) throws SQLException {
        Film attached = em.find(Film.class, film.getIdImdb());
        if (attached != null) {
            em.remove(attached);
            return true;
        }
        return false;
    }

    public Film findById(String idImdb) {
        return em.find(Film.class, idImdb);
    }

    public boolean existsById(String idImdb) {
        return findById(idImdb) != null;
    }
}
