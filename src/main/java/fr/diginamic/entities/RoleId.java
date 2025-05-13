package fr.diginamic.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe représentant une clé composite pour l'entité {@link Role}.
 * Cette classe est utilisée comme identifiant composite pour les rôles joués par des acteurs dans des films.
 * Elle implémente {@link Serializable} pour permettre la sérialisation.
 */
public class RoleId implements Serializable {

    /**
     * L'identifiant du film associé à ce rôle.
     */
    private String film;

    /**
     * L'identifiant de l'acteur associé à ce rôle.
     */
    private String acteur;

    /**
     * Constructeur par défaut requis pour JPA.
     */
    public RoleId() {}

    /**
     * Constructeur pour créer une clé composite avec un film et un acteur spécifiés.
     *
     * @param film   L'identifiant du film.
     * @param acteur L'identifiant de l'acteur.
     */
    public RoleId(String film, String acteur) {
        this.film = film;
        this.acteur = acteur;
    }

    /**
     * Compare cet objet à un autre pour l'égalité.
     *
     * @param o L'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleId)) return false;
        RoleId that = (RoleId) o;
        return Objects.equals(film, that.film) &&
                Objects.equals(acteur, that.acteur);
    }

    /**
     * Génère un code de hachage pour cet objet.
     *
     * @return Le code de hachage.
     */
    @Override
    public int hashCode() {
        return Objects.hash(film, acteur);
    }
}