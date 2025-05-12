package fr.diginamic.mapper;

import fr.diginamic.dto.RoleDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Role;

public class RoleMapper {

    public static Role toEntity(RoleDto dto, Film film) {
        if (dto == null) return null;

        Role role = new Role();
        role.setFilm(film);
        role.setActeur(ActeurMapper.toEntity(dto.getActeur()));
        role.setPersonnage(dto.getPersonnage());

        return role;
    }
}
