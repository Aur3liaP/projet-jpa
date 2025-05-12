package fr.diginamic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO (Data Transfer Object) pour la représentation d'un Lieu.
 * Cette classe est utilisée pour faciliter la sérialisation/désérialisation
 * avec Jackson lors des échanges JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaissanceDto {

    private String dateNaissance;
    private String lieuNaissance;

    /**
     * Constructeur par défaut
     */
    public NaissanceDto() {
    }

    /**
     * Constructeur avec paramètres
     *
     * @param dateNaissance de l'acteur/realisateur
     * @param lieuNaissance de l'acteur/realisateur
     */
    public NaissanceDto(String dateNaissance, String lieuNaissance) {
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
    }

    /**
     * Getters et Setters
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NaissanceDto{");
        sb.append("dateNaissance='").append(dateNaissance).append('\'');
        sb.append(", lieuNaissance=").append(lieuNaissance);
        sb.append('}');
        return sb.toString();
    }
}
