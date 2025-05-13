package fr.diginamic.mapper;

import fr.diginamic.entities.Genre;

/**
 * Mapper pour convertir un nom de genre en une entité de genre.
 * Cette classe fournit des méthodes pour transformer un nom de genre en une entité {@link Genre}.
 */
public class GenreMapper {
    /**
     * Convertit un nom de genre en une entité de genre.
     *
     * @param genreNom Le nom du genre à convertir.
     * @return L'entité de genre convertie, ou null si le nom de genre est null.
     */
    public static Genre toEntity(String genreNom) {
        if (genreNom == null) return null;

        Genre genre = new Genre();
        genre.setNom(genreNom);
        return genre;
    }
}
