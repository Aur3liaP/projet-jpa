package fr.diginamic.dao;

import fr.diginamic.entities.Acteur;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Role;
import jakarta.persistence.EntityManager;
import java.util.List;

public class RoleDao implements BaseDao<Role> {

    private final EntityManager em;

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

    public Role findByFilmAndActeur(Film film, Acteur acteur) {
        List<Role> result = em.createQuery("SELECT r FROM Role r WHERE r.film = :film AND r.acteur = :acteur", Role.class)
                .setParameter("film", film)
                .setParameter("acteur", acteur)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
