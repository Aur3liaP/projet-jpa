package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entité représentant un genre cinématographique dans la base de données.
 * Un genre peut être associé à plusieurs films.
 */
@Entity
@Table(name = "genres")
public class Genre {

    /** Identifiant unique auto-généré */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GENRE")
    private Integer id;

    /** Nom du genre */
    @Column(name = "NOM", length = 50, nullable = false)
    private String nom;

    /** Films associés à ce genre */
    @ManyToMany(mappedBy = "genres")
    private List<Film> films = new ArrayList<>();

    /**
     * Constructeur par défaut
     */
    public Genre() {
    }

    /**
     * Constructeur avec nom
     * @param nom Nom du genre
     */
    public Genre(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère l'identifiant du genre
     * @return identifiant du genre
     */
    public Integer getId() {
        return id;
    }

    /**
     * Définit l'identifiant du genre
     * @param id Nouvel identifiant
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Récupère le nom du genre
     * @return Nom du genre
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du genre
     * @param nom Nouveau nom du genre
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère les films associés à ce genre
     * @return Ensemble des films du genre
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Définit les films associés à ce genre
     * @param films Nouvel ensemble de films
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id != null && id.equals(genre.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Genre{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append('}');
        return sb.toString();
    }
}