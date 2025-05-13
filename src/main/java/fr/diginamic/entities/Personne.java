package fr.diginamic.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entité représentant une personne dans la base de données.
 * Cette classe sert de base pour les acteurs et réalisateurs.
 */
@Entity
@Table(name = "personnes", indexes = {
        @Index(name = "idx_id_imdb", columnList = "ID_IMDB"),
})
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne {

    /** Identifiant unique IMDb */
    @Id
    @Column(name = "ID_IMDB", length = 50)
    private String idImdb;

    /** Nom complet de la personne */
    @Column(name = "NOM", length = 50)
    private String nom;

    /** Identité complète */
    @Column(name = "IDENTITE", length = 100)
    private String identite;

    /** Date de naissance */
    @Column(name = "DATE_NAISSANCE")
    private LocalDate dateNaissance;

    /** URL vers la page de la personne */
    @Column(name = "URL", length = 255)
    private String url;

    /** Lieu de naissance de la personne */
    @ManyToOne
    @JoinColumn(name = "ID_LIEU_NAISSANCE")
    private Lieu lieuNaissance;

    /**
     * Constructeur par défaut
     */
    public Personne() {
    }

    /**
     * Constructeur avec paramètres essentiels
     *
     * @param idImdb identifiant unique IMDb
     * @param identite identité complète
     */
    public Personne(String idImdb, String identite) {
        this.idImdb = idImdb;
        this.identite = identite;
    }

    /**
     * Récupère l'identifiant IMDb
     * @return identifiant IMDb
     */
    public String getIdImdb() {
        return idImdb;
    }

    /**
     * Définit l'identifiant IMDb
     * @param idImdb Nouvel identifiant
     */
    public void setIdImdb(String idImdb) {
        this.idImdb = idImdb;
    }

    /**
     * Récupère le nom de la personne
     * @return Nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la personne
     * @param nom Nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère l'identité complète
     * @return Identité
     */
    public String getIdentite() {
        return identite;
    }

    /**
     * Définit l'identité complète
     * @param identite Nouvelle identité
     */
    public void setIdentite(String identite) {
        this.identite = identite;
    }

    /**
     * Récupère la date de naissance
     * @return Date de naissance
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la date de naissance
     * @param dateNaissance Nouvelle date
     */
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Récupère l'URL
     * @return URL de la page
     */
    public String getUrl() {
        return url;
    }

    /**
     * Définit l'URL
     * @param url Nouvelle URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Récupère le lieu de naissance
     * @return Lieu de naissance
     */
    public Lieu getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * Définit le lieu de naissance
     * @param lieuNaissance Nouveau lieu
     */
    public void setLieuNaissance(Lieu lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return idImdb != null && idImdb.equals(personne.idImdb);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Personne{");
        sb.append("idImdb='").append(idImdb).append('\'');
        sb.append(", identite='").append(identite).append('\'');
        sb.append(", dateNaissance=").append(dateNaissance);
        sb.append(", lieuNaissance=").append(lieuNaissance != null ? lieuNaissance.getVille() : "null");
        sb.append('}');
        return sb.toString();
    }
}