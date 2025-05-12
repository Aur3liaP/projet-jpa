package fr.diginamic.mapper;

import fr.diginamic.dto.*;
import fr.diginamic.entities.Film;

import java.util.stream.Collectors;

public class FilmMapper {

    public static Film toFilm(FilmDto dto) {
        if (dto == null) return null;

        Film film = new Film();
        film.setIdImdb(dto.getIdImdb());
        film.setTitre(dto.getTitre());
        film.setUrl(dto.getUrl());
        film.setRating(dto.getRating());
        film.setResume(dto.getResume());
        film.setLangue(dto.getLangue());
        film.setAnnee(dto.getAnnee());

        film.setPays(PaysMapper.toEntity(dto.getPays()));
        film.setLieuTournage(LieuMapper.toEntity(dto.getLieuTournage()));
//
//        film.setRealisateurs(
//                dto.getRealisateurs().stream()
//                        .map(RealisateurMapper::toEntity)
//                        .collect(Collectors.toList())
//        );
//
//        film.setActeurs(
//                dto.getActeursPrincipaux().stream()
//                        .map(ActeurMapper::toEntity)
//                        .collect(Collectors.toList())
//        );
//
//        film.setRoles(
//                dto.getRoles().stream()
//                        .map(RoleMapper::toEntity)
//                        .collect(Collectors.toList())
//        );
//
//        film.setGenres(
//                dto.getGenres().stream()
//                        .map(GenreMapper::toEntity)
//                        .collect(Collectors.toList())
//        );

        return film;
    }
}
