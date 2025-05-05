package fr.diginamic.entities;

import java.io.Serializable;
import java.util.Objects;

public class RoleId implements Serializable {
    private String film;
    private String acteur;

    public RoleId() {}

    public RoleId(String film, String acteur) {
        this.film = film;
        this.acteur = acteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleId)) return false;
        RoleId that = (RoleId) o;
        return Objects.equals(film, that.film) &&
                Objects.equals(acteur, that.acteur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, acteur);
    }
}