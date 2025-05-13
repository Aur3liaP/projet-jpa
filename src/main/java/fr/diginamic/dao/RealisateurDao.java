package fr.diginamic.dao;

import fr.diginamic.entities.Realisateur;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Classe DAO pour gérer les opérations de persistance sur les Realisateurs
 */
public class RealisateurDao implements BaseDao<Realisateur> {
    private final EntityManager em;

    /**
     * Constructeur avec injection de l'EntityManager
     * @param em EntityManager à utiliser
     */
    public RealisateurDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Realisateur> extraire() {
        return em.createQuery("SELECT r FROM Realisateur r", Realisateur.class).getResultList();
    }

    @Override
    public void insert(Realisateur realisateur) {
        em.persist(realisateur);
    }

    @Override
    public int update(Realisateur realisateur) {
        em.merge(realisateur);
        return 1;
    }

    @Override
    public boolean delete(Realisateur realisateur) {
        em.remove(em.contains(realisateur) ? realisateur : em.merge(realisateur));
        return true;
    }

    /**
     * Recherche un réalisateur par son ID IMDb
     * @param idImdb ID IMDb du réalisateur à rechercher
     * @return Réalisateur trouvé ou null
     */
    public Realisateur findById(String idImdb) {
        List<Realisateur> result = em.createQuery("SELECT r FROM Realisateur r WHERE r.idImdb = :idImdb", Realisateur.class)
                .setParameter("idImdb", idImdb)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Recherche un réalisateur par son identité
     * @param identite Identité du réalisateur à rechercher
     * @return Réalisateur trouvé ou null
     */
    public Realisateur findByIdentite(String identite) {
        List<Realisateur> result = em.createQuery("SELECT r FROM Realisateur r WHERE r.identite = :identite", Realisateur.class)
                .setParameter("identite", identite)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}