package fr.diginamic.service;

import fr.diginamic.dao.RoleDao;
import fr.diginamic.entities.Role;
import jakarta.persistence.EntityManager;

/**
 * Service dédié à la gestion des entités Role.
 */
public class RoleService {

    private final RoleDao roleDao;

    public RoleService(EntityManager em) {
        this.roleDao = new RoleDao(em);
    }

    /**
     * Récupère un rôle existant ou en crée un nouveau s'il n'existe pas.
     *
     * @param role Le rôle à rechercher ou créer.
     * @return Le rôle existant s'il est trouvé, sinon le nouveau rôle créé.
     */
    public Role getOrCreateRole(Role role) {
        Role existant = roleDao.findByFilmAndActeur(role.getFilm(), role.getActeur());
        if (existant != null) {
            return existant;
        } else {
            roleDao.insert(role);
            return role;
        }
    }
}
