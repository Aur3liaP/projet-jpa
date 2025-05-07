package fr.diginamic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.diginamic.entities.Pays;

/**
 * DTO (Data Transfer Object) pour la représentation d'un Lieu.
 * Cette classe est utilisée pour faciliter la sérialisation/désérialisation
 * avec Jackson lors des échanges JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class LieuDto {

    @JsonProperty("etatDept")
    private String etatRegion;

    @JsonProperty("ville")
    private String ville;

    @JsonProperty("pays")
    private String pays;

    /**
     * Constructeur par défaut
     */
    public LieuDto() {
    }

    /**
     * Constructeur avec etat/region et pays
     *
     * @param etatRegion l'état/Region du lieu
     * @param pays le pays du lieu
     */
    public LieuDto(String etatRegion, String pays) {
        this.etatRegion = etatRegion;
        this.pays = pays;
    }

    /**
     * Constructeur avec ville, etat/region et pays
     *
     * @param ville la ville du lieu
     * @param etatRegion l'état/Region du lieu
     * @param pays le pays du lieu
     */
    public LieuDto(String etatRegion, String ville, String pays) {
        this.ville = ville;
        this.etatRegion = etatRegion;
        this.pays = pays;
    }


    public String getEtatRegion() {
        return etatRegion;
    }

    public void setEtatRegion(String etatRegion) {
        this.etatRegion = etatRegion;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LieuDto{");
        sb.append(", etatRegion='").append(etatRegion).append('\'');
        sb.append(", ville='").append(ville).append('\'');
        sb.append(", pays=").append(pays);
        sb.append('}');
        return sb.toString();
    }
}
