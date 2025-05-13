package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un réalisateur dans la base de données.
 * Cette classe hérite de Personne et ajoute des attributs spécifiques aux réalisateurs.
 */
@Entity
@Table(name = "realisateurs")
public class Realisateur extends Personne {

    /** Films réalisés par cette personne */
    @ManyToMany(mappedBy = "realisateurs")
    private List<Film> films = new ArrayList<>();

    /**
     * Constructeur par défaut
     */
    public Realisateur() {
        super();
    }

    /**
     * Constructeur avec paramètres essentiels
     *
     * @param idImdb identifiant unique IMDb
     * @param identite identité complète
     */
    public Realisateur(String idImdb, String identite) {
        super(idImdb, identite);
    }

    /**
     * Récupère les films réalisés
     * @return Ensemble des films du realisateur
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Définit les films réalisés
     * @param films Nouvel ensemble de films
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Realisateur{");
        sb.append("idImdb='").append(getIdImdb()).append('\'');
        sb.append(", identite='").append(getIdentite()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}