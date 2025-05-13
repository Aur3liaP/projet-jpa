package fr.diginamic.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un pays dans la base de données.
 * Un pays peut être associé à des films et à des lieux.
 */
@Entity
@Table(name = "pays", indexes = {
        @Index(name = "idx_nom", columnList = "NOM"),
})
public class Pays {

    /** Nom du pays unique */
    @Id
    @Column(name = "NOM", length = 50, nullable = false)
    private String nom;

    /** URL associée au pays */
    @Column(name = "URL", length = 255)
    private String url;

    /** Liste des lieux situés dans ce pays */
    @OneToMany(mappedBy = "pays")
    private List<Lieu> lieux = new ArrayList<>();

    /** Liste des films produits dans ce pays */
    @OneToMany(mappedBy = "pays")
    private List<Film> films = new ArrayList<>();

    /**
     * Constructeur par défaut
     */
    public Pays() {
    }

    /**
     * Constructeur avec nom
     * @param nom Nom du pays
     */
    public Pays(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le nom du pays
     * @return Nom du pays
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du pays
     * @param nom Nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère l'URL associée au pays
     * @return URL du pays
     */
    public String getUrl() {
        return url;
    }

    /**
     * Définit l'URL associée au pays
     * @param url Nouvelle URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Récupère la liste des lieux situés dans ce pays
     * @return Ensemble des lieux
     */
    public List<Lieu> getLieux() {
        return lieux;
    }

    /**
     * Définit la liste des lieux situés dans ce pays
     * @param lieux Nouvel ensemble de lieux
     */
    public void setLieux(List<Lieu> lieux) {
        this.lieux = lieux;
    }

    /**
     * Récupère la liste des films produits dans ce pays
     * @return Ensemble des films
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Définit la liste des films produits dans ce pays
     * @param films Nouvel ensemble de films
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pays{");
        sb.append("nom='").append(nom);
        sb.append('}');
        return sb.toString();
    }
}
