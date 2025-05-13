package fr.diginamic.dao;

import fr.diginamic.entities.Lieu;
import fr.diginamic.entities.Pays;
import jakarta.persistence.EntityManager;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO pour gérer les opérations de persistance sur les Lieux
 */
public class LieuDao implements BaseDao<Lieu> {

    private final EntityManager em;

    /**
     * Constructeur avec injection de l'EntityManager
     * @param em EntityManager à utiliser
     */
    public LieuDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Lieu> extraire() {
        TypedQuery<Lieu> query = em.createQuery("SELECT l FROM Lieu l", Lieu.class);
        return query.getResultList();
    }

    @Override
    public void insert(Lieu lieu){
        em.persist(lieu);
        em.flush();
    }

    @Override
    public int update(Lieu lieu){
        em.merge(lieu);
        return 1;
    }

    @Override
    public boolean delete(Lieu lieu){
        Lieu attachedLieu = em.contains(lieu) ? lieu : em.merge(lieu);
        em.remove(attachedLieu);
        return true;
    }

    /**
     * Recherche un Lieu par son détail (ville, état, pays)
     * @param ville ville du lieu à rechercher
     * @param etatRegion etat/Region du lieu à rechercher
     * @param pays Pays du lieu à rechercher
     * @return Lieu trouvé ou null
     */
    public Lieu findByDetails(String ville, String etatRegion, Pays pays) {
        TypedQuery<Lieu> query = em.createQuery(
                "SELECT l FROM Lieu l WHERE l.ville = :ville AND l.etatRegion = :etat AND l.pays.nom = :pays",
                Lieu.class
        );
        query.setParameter("ville", ville);
        query.setParameter("etat", etatRegion);
        query.setParameter("pays", pays.getNom());

        List<Lieu> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
