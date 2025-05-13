package fr.diginamic.entities;

import jakarta.persistence.*;

/**
 * Représente un rôle joué par un acteur dans un film.
 * Cette classe est une entité JPA avec une clé composite composée de {@link Film} et {@link Acteur}.
 */
@Entity
@IdClass(RoleId.class)
public class Role {

    /**
     * Le film associé à ce rôle.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_FILM")
    private Film film;

    /**
     * L'acteur associé à ce rôle.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_ACTEUR")
    private Acteur acteur;

    /**
     * Le nom du personnage joué par l'acteur dans le film.
     */
    private String personnage;

    /**
     * Constructeur par défaut requis par JPA.
     */
    public Role() {}

    /**
     * Constructeur pour créer un rôle avec un film, un acteur et un personnage spécifiés.
     *
     * @param film       Le film associé à ce rôle.
     * @param acteur     L'acteur associé à ce rôle.
     * @param personnage Le nom du personnage joué par l'acteur.
     */
    public Role(Film film, Acteur acteur, String personnage) {
        this.film = film;
        this.acteur = acteur;
        this.personnage = personnage;
    }

    /**
     * Obtient le film associé à ce rôle.
     *
     * @return Le film associé.
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Définit le film associé à ce rôle.
     *
     * @param film Le film à associer.
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * Obtient l'acteur associé à ce rôle.
     *
     * @return L'acteur associé.
     */
    public Acteur getActeur() {
        return acteur;
    }

    /**
     * Définit l'acteur associé à ce rôle.
     *
     * @param acteur L'acteur à associer.
     */
    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    /**
     * Obtient le nom du personnage joué par l'acteur.
     *
     * @return Le nom du personnage.
     */
    public String getPersonnage() {
        return personnage;
    }

    /**
     * Définit le nom du personnage joué par l'acteur.
     *
     * @param personnage Le nom du personnage à définir.
     */
    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de ce rôle.
     *
     * @return Une chaîne de caractères représentant ce rôle.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("film=").append(film);
        sb.append(", acteur=").append(acteur);
        sb.append(", personnage='").append(personnage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}