package fr.diginamic.dao;

import fr.diginamic.entities.Pays;
import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * Classe DAO pour gérer les opérations de persistance sur les Pays
 */
public class PaysDao implements BaseDao<Pays> {

    private final EntityManager em;

    /**
     * Constructeur avec injection de l'EntityManager
     * @param em EntityManager à utiliser
     */
    public PaysDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Pays> extraire() {
        return em.createQuery("SELECT p FROM Pays p", Pays.class).getResultList();
    }

    @Override
    public void insert(Pays pays)  {
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

    /**
     * Recherche un pays par son nom
     * @param nom nom du pays à rechercher
     * @return Pays trouvé ou null
     */
    public Pays findByNom(String nom) {
        List<Pays> result = em.createQuery("SELECT p FROM Pays p WHERE p.nom = :nom", Pays.class)
                .setParameter("nom", nom)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
