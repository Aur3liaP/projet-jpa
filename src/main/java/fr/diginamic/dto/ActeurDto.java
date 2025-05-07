package fr.diginamic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO (Data Transfer Object) pour la représentation d'un Acteur.
 * Cette classe est utilisée pour faciliter la sérialisation/désérialisation
 * avec Jackson lors des échanges JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActeurDto {
    private String id;
    private String identite;
    private String url;
    private NaissanceDto naissance;

    /**
     * Constructeur par défaut
     */
    public ActeurDto() {
    }

    /**
     * Constructeur avec paramètres
     *
     * @param id l'identifiant du réalisateur
     * @param identite le nom complet du réalisateur
     * @param url l'URL associée au réalisateur
     * @param naissance les informations de naissance du réalisateur
     */
    public ActeurDto(String id, String identite, String url, NaissanceDto naissance) {
        this.id = id;
        this.identite = identite;
        this.url = url;
        this.naissance = naissance;
    }

    /**
     * Getters et setters
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public NaissanceDto getNaissance() {
        return naissance;
    }

    public void setNaissance(NaissanceDto naissance) {
        this.naissance = naissance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActeurDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", identite='").append(identite).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", naissance=").append(naissance);
        sb.append('}');
        return sb.toString();
    }
}
