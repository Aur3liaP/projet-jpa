package fr.diginamic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO (Data Transfer Object) pour la représentation d'un Pays.
 * Cette classe est utilisée pour faciliter la sérialisation/désérialisation
 * avec Jackson lors des échanges JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaysDto {

    private String nom;

    private String url;

    /**
     * Constructeur par défaut
     */
    public PaysDto() {
    }

    /**
     * Constructeur avec le nom et l'URL du pays
     *
     * @param nom le nom du pays
     * @param url l'URL associée au pays
     */
    public PaysDto(String nom, String url) {
        this.nom = nom;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PaysDto{");
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
