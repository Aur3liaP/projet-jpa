package fr.diginamic.service;

import fr.diginamic.dao.RoleDao;
import fr.diginamic.entities.Role;
import jakarta.persistence.EntityManager;

public class RoleService {

    private final RoleDao roleDao;

    public RoleService(EntityManager em) {
        this.roleDao = new RoleDao(em);
    }

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
