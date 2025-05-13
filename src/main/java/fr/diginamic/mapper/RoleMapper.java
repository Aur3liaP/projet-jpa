package fr.diginamic.mapper;

import fr.diginamic.dto.RoleDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Role;

/**
 * Mapper pour convertir des DTOs de rôle en entités de rôle.
 * Cette classe fournit des méthodes pour transformer un {@link RoleDto} en une entité {@link Role}.
 */
public class RoleMapper {

    /**
     * Convertit un DTO de rôle en une entité de rôle.
     *
     * @param dto  Le DTO de rôle à convertir.
     * @param film Le film associé au rôle.
     * @return L'entité de rôle convertie, ou null si le DTO est null.
     */
    public static Role toEntity(RoleDto dto, Film film) {
        if (dto == null) return null;

        Role role = new Role();
        role.setFilm(film);
        role.setActeur(ActeurMapper.toEntity(dto.getActeur()));
        role.setPersonnage(dto.getPersonnage());

        return role;
    }
}
