package fr.diginamic.dao;

import fr.diginamic.entities.Genre;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenreDao implements BaseDao<Genre>{

    private final EntityManager em;

    public GenreDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Genre> extraire(){
        return em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }

    @Override
    public void insert(Genre genre) {
        em.persist(genre);
    }

    @Override
    public int update(Genre genre)  {
        em.merge(genre);
        return 1;
    }

    @Override
    public boolean delete(Genre genre){
        em.remove(em.contains(genre) ? genre : em.merge(genre));
        return true;
    }

    public Genre findById(Integer id) {
        List<Genre> result = em.createQuery("SELECT p FROM Genre p WHERE p.id = :id", Genre.class)
                .setParameter("id", id)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public Genre findByNom(String nom) {
        List<Genre> result = em.createQuery("SELECT g FROM Genre g WHERE g.nom = :nom", Genre.class)
                .setParameter("nom", nom)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
