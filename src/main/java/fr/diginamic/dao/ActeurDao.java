package fr.diginamic.dao;

import fr.diginamic.entities.Acteur;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Classe DAO pour gérer les opérations de persistance sur les Acteurs
 */
public class ActeurDao implements BaseDao<Acteur> {

    private final EntityManager em;

    /**
     * Constructeur avec injection de l'EntityManager
     * @param em EntityManager à utiliser
     */
    public ActeurDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Acteur> extraire() {
        return em.createQuery("SELECT a FROM Acteur a", Acteur.class).getResultList();
    }

    @Override
    public void insert(Acteur acteur) {
        em.persist(acteur);
    }

    @Override
    public int update(Acteur acteur) {
        em.merge(acteur);
        return 1;
    }

    @Override
    public boolean delete(Acteur acteur) {
        em.remove(em.contains(acteur) ? acteur : em.merge(acteur));
        return true;
    }

    /**
     * Recherche un acteur par son ID IMDb
     * @param idImdb ID IMDb de l'acteur à rechercher
     * @return Acteur trouvé ou null
     */
    public Acteur findById(String idImdb) {
        List<Acteur> result = em.createQuery("SELECT a FROM Acteur a WHERE a.idImdb = :idImdb", Acteur.class)
                .setParameter("idImdb", idImdb)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}