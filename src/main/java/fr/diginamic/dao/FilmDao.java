package fr.diginamic.dao;

import fr.diginamic.entities.Film;
import jakarta.persistence.*;

import java.util.List;

/**
 * DAO spécifique pour l'entité Film.
 */
/**
 * DAO spécifique pour l'entité Film.
 */
public class FilmDao implements BaseDao<Film> {

    private final EntityManager em;

    public FilmDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Film> extraire(){
        return em.createQuery("SELECT f FROM Film f", Film.class).getResultList();
    }

    @Override
    public void insert(Film film){
        em.persist(film);
    }

    @Override
    public int update(Film film){
        em.merge(film);
        return 1;
    }

    @Override
    public boolean delete(Film film){
        Film attached = em.find(Film.class, film.getIdImdb());
        if (attached != null) {
            em.remove(attached);
            return true;
        }
        return false;
    }

    public Film findById(String idImdb){
        return em.find(Film.class, idImdb);
    }

    public boolean existsById(String idImdb){
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(f) FROM Film f WHERE f.idImdb = :id", Long.class);
        query.setParameter("id", idImdb);
        return query.getSingleResult() > 0;
    }
}
