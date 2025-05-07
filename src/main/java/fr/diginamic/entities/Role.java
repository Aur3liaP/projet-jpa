package fr.diginamic.entities;

import jakarta.persistence.*;

@Entity
@IdClass(RoleId.class)
public class Role {

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_FILM")
    private Film film;

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_ACTEUR")
    private Acteur acteur;

    private String personnage;

    public Role() {}

    public Role(Film film, Acteur acteur, String personnage) {
        this.film = film;
        this.acteur = acteur;
        this.personnage = personnage;
    }


    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Acteur getActeur() {
        return acteur;
    }

    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    public String getPersonnage() {
        return personnage;
    }

    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }

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

