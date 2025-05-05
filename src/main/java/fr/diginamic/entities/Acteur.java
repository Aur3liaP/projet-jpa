package fr.diginamic.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un acteur dans la base de données.
 * Cette classe hérite de Personne et ajoute des attributs spécifiques aux acteurs.
 */
@Entity
@Table(name = "acteurs")
public class Acteur extends Personne {

    /** Taille de l'acteur en mètres */
    @Column(name = "TAILLE", precision = 5, scale = 2)
    private Double taille;

    /** Films dans lesquels l'acteur a joué un rôle principal */
    @ManyToMany(mappedBy = "acteurs")
    private List<Film> films = new ArrayList<>();

    /** Rôles joués par l'acteur */
    @OneToMany(mappedBy = "acteur")
    private List<Role> roles = new ArrayList<>();

    /**
     * Constructeur par défaut
     */
    public Acteur() {
        super();
    }

    /**
     * Constructeur avec paramètres essentiels
     *
     * @param idImdb identifiant unique IMDb
     * @param identite identité complète
     */
    public Acteur(String idImdb, String identite) {
        super(idImdb, identite);
    }

    /**
     * Récupère la taille de l'acteur
     * @return Taille en mètres
     */
    public Double getTaille() {
        return taille;
    }

    /**
     * Définit la taille de l'acteur
     * @param taille Nouvelle taille en mètres
     */
    public void setTaille(Double taille) {
        this.taille = taille;
    }

    /**
     * Récupère les films où l'acteur tient un rôle principal
     * @return Ensemble des castings principaux
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Définit les films où l'acteur tient un rôle principal
     * @param films Nouvel ensemble de films
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    /**
     * Récupère les rôles joués par l'acteur
     * @return Ensemble des rôles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Définit les rôles joués par l'acteur
     * @param roles Nouvel ensemble de rôles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Acteur{");
        sb.append("idImdb='").append(getIdImdb()).append('\'');
        sb.append(", identite='").append(getIdentite()).append('\'');
        sb.append(", taille=").append(taille);
        sb.append('}');
        return sb.toString();
    }
}