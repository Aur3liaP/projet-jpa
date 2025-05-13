package fr.diginamic.mapper;

import fr.diginamic.dto.*;
import fr.diginamic.entities.Film;
import fr.diginamic.util.RatingUtils;

import java.util.stream.Collectors;

/**
 * Mapper pour convertir les FilmDto en entités Film.
 * Gère la transformation des objets de transfert de données (DTO) en objets d'entité.
 */
public class FilmMapper {

    /**
     * Convertit un FilmDto en entité Film.
     *
     * @param dto Le DTO du film à convertir
     * @return L'entité Film correspondante, ou null si le DTO est null
     */
    public static Film toFilm(FilmDto dto) {
        if (dto == null) return null;

        Film film = new Film();
        film.setIdImdb(dto.getIdImdb());
        film.setTitre(dto.getTitre());
        film.setUrl(dto.getUrl());
        film.setRating(RatingUtils.parseRating(dto.getRating()));
        film.setResume(dto.getResume());
        film.setLangue(dto.getLangue());
        film.setAnnee(dto.getAnnee());

        film.setPays(PaysMapper.toEntity(dto.getPays()));
        film.setLieuTournage(LieuMapper.toEntity(dto.getLieuTournage()));

        film.setRealisateurs(
                dto.getRealisateurs().stream()
                        .map(RealisateurMapper::toEntity)
                        .collect(Collectors.toList())
        );

        film.setGenres(
                dto.getGenres().stream()
                        .map(GenreMapper::toEntity)
                        .collect(Collectors.toList())
        );

        return film;
    }
}
