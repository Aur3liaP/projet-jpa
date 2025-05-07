package fr.diginamic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * DTO (Data Transfer Object) pour la représentation d'un Lieu.
 * Cette classe est utilisée pour faciliter la sérialisation/désérialisation
 * avec Jackson lors des échanges JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LieuTournageDto {

    private String ville;

    @JsonProperty("etatRegion")
    private String etatRegion;

    private PaysDto pays;

    /**
     * Constructeur par défaut
     */
    public LieuTournageDto() {
    }

    /**
     * Constructeur avec paramètres
     *
     * @param ville le nom de la ville
     * @param etatRegion l'état ou le département
     * @param pays le pays
     */
    public LieuTournageDto(String ville, String etatRegion, PaysDto pays) {
        this.ville = ville;
        this.etatRegion = etatRegion;
        this.pays = pays;
    }

    /**
     * Getters et Setters
     */

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEtatRegion() {
        return etatRegion;
    }

    public void setEtatRegion(String etatRegion) {
        this.etatRegion = etatRegion;
    }

    public PaysDto getPays() {
        return pays;
    }

    public void setPays(PaysDto pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LieuTournageDto{");
        sb.append("ville='").append(ville).append('\'');
        sb.append(", etatRegion='").append(etatRegion).append('\'');
        sb.append(", pays=").append(pays);
        sb.append('}');
        return sb.toString();
    }
}
