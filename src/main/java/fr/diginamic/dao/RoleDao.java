package fr.diginamic.dao;

import fr.diginamic.entities.Acteur;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Role;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Classe DAO pour gérer les opérations de persistance sur les Roles des Acteurs
 */
public class RoleDao implements BaseDao<Role> {

    private final EntityManager em;

    /**
     * Constructeur avec injection de l'EntityManager
     * @param em EntityManager à utiliser
     */
    public RoleDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Role> extraire() {
        return em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public void insert(Role role) {
        em.persist(role);
    }

    @Override
    public int update(Role role) {
        em.merge(role);
        return 1;
    }

    @Override
    public boolean delete(Role role) {
        em.remove(em.contains(role) ? role : em.merge(role));
        return true;
    }

    /**
     * Recherche le role d'un acteur par rapport au film
     * @param film film dans lequel est joué le rôle
     * @param acteur acteur qui joue le rôle
     * @return rôle trouvé ou null
     */
    public Role findByFilmAndActeur(Film film, Acteur acteur) {
        List<Role> result = em.createQuery("SELECT r FROM Role r WHERE r.film = :film AND r.acteur = :acteur", Role.class)
                .setParameter("film", film)
                .setParameter("acteur", acteur)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
