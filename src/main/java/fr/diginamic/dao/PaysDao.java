package fr.diginamic.dao;

import fr.diginamic.entities.Pays;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PaysDao implements BaseDao<Pays> {

    private final EntityManager em;

    public PaysDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Pays> extraire() {
        return em.createQuery("SELECT p FROM Pays p", Pays.class).getResultList();
    }

    @Override
    public void insert(Pays pays) {
        em.persist(pays);
    }

    @Override
    public int update(Pays pays) {
        em.merge(pays);
        return 1;
    }

    @Override
    public boolean delete(Pays pays) {
        em.remove(em.contains(pays) ? pays : em.merge(pays));
        return true;
    }

    public Pays findByNom(String nom) {
        List<Pays> result = em.createQuery("SELECT p FROM Pays p WHERE p.nom = :nom", Pays.class)
                .setParameter("nom", nom)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
