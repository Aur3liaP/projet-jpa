package fr.diginamic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO (Data Transfer Object) pour la représentation d'un Genre de film.
 * Cette classe est utilisée pour faciliter la sérialisation/désérialisation
 * avec Jackson lors des échanges JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreDto {
    private String nom;

    /**
     * Constructeur par défaut
     */
    public GenreDto() {
    }

    /**
     * Constructeur avec paramètres
     *
     * @param nom du genre du film
     */
    public GenreDto(String nom) {
        this.nom = nom;
    }

    /**
     * Getters et Setters
     */

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GenreDto{");
        sb.append("nom='").append(nom).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
