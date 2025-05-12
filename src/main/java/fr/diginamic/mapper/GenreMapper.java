package fr.diginamic.mapper;

import fr.diginamic.entities.Genre;

public class GenreMapper {
    public static Genre toEntity(String genreNom) {
        if (genreNom == null) return null;

        Genre genre = new Genre();
        genre.setNom(genreNom);
        return genre;
    }
}
