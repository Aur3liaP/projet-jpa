package fr.diginamic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) pour la représentation d'un Role.
 * Cette classe est utilisée pour faciliter la sérialisation/désérialisation
 * avec Jackson lors des échanges JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {

    @JsonProperty("characterName")
    private String personnage;

    private ActeurDto acteur;

    /**
     * Constructeur par défaut
     */
    public RoleDto() {
    }

    /**
     * Constructeur avec paramètres
     *
     * @param personnage le nom du personnage
     * @param acteur l'acteur jouant le rôle
     */
    public RoleDto(String personnage, ActeurDto acteur) {
        this.personnage = personnage;
        this.acteur = acteur;
    }

    /**
     * Getters et setters
     */

    public String getPersonnage() {
        return personnage;
    }

    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }

    public ActeurDto getActeur() {
        return acteur;
    }

    public void setActeur(ActeurDto acteur) {
        this.acteur = acteur;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleDto{");
        sb.append("personnage='").append(personnage).append('\'');
        sb.append(", acteur=").append(acteur);
        sb.append('}');
        return sb.toString();
    }
}
