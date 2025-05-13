package fr.diginamic.dao;

import fr.diginamic.entities.Film;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO pour gérer les opérations de persistance sur les Films
 */
public class FilmDao implements BaseDao<Film> {

    private final EntityManager em;

    /**
     * Constructeur avec injection de l'EntityManager
     * @param em EntityManager à utiliser
     */
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

    /**
     * Recherche si un film est existant par son ID IMDb
     * @param idImdb ID IMDb du film à rechercher
     * @return Film trouvé si existant
     */
    public boolean existsById(String idImdb){
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(f) FROM Film f WHERE f.idImdb = :id", Long.class);
        query.setParameter("id", idImdb);
        return query.getSingleResult() > 0;
    }
}
