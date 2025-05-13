package fr.diginamic.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un lieu géographique dans la base de données.
 * Un lieu peut être associé à des films (lieu de tournage) et à des personnes (lieu de naissance).
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "lieux", indexes = {
        @Index(name = "idx_ville", columnList = "VILLE"),
        @Index(name = "idx_etat_region", columnList = "ETAT_REGION"),
        @Index(name = "idx_pays_id", columnList = "ID_PAYS")
})
public class Lieu {

    /** Identifiant unique auto-généré */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIEU", nullable = false)
    private Integer id;

    /** État ou région du lieu */
    @Column(name = "ETAT_REGION", length = 100)
    private String etatRegion;

    /** Ville du lieu */
    @Column(name = "VILLE", length = 100)
    private String ville;

    /** Libellé complet du lieu */
    @Column(name = "LIBELLE")
    private String libelle;

    /** Pays auquel appartient ce lieu */
    @ManyToOne
    @JoinColumn(name = "PAYS")
    private Pays pays;

    /** Liste des films tournés dans ce lieu */
    @OneToMany(mappedBy = "lieuTournage")
    private List<Film> lieuTournage = new ArrayList<>();

    /** Liste des personnes nées dans ce lieu */
    @OneToMany(mappedBy = "lieuNaissance")
    private List<Personne> lieuNaissance = new ArrayList<>();

    /**
     * Constructeur par défaut
     */
    public Lieu() {
    }

    /**
     * Constructeur complet
     *
     * @param etatRegion l'état ou la région
     * @param ville la ville
     * @param libelle le libellé complet
     * @param pays le pays
     */
    public Lieu(String etatRegion, String ville, String libelle, Pays pays) {
        this.etatRegion = etatRegion;
        this.ville = ville;
        this.libelle = libelle;
        this.pays = pays;
    }

    /**
     * Récupère l'ID du lieu
     * @return id du lieu
     */
    public Integer getId() {
        return id;
    }

    /**
     * Définit l'ID du lieu
     * @param id Nouvel id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Récupère le nom de l'état/ la region du lieu
     * @return Nom de l'état/ la region
     */
    public String getEtatRegion() {
        return etatRegion;
    }

    /**
     * Définit le nom de l'état/ la region du lieu
     * @param etatRegion Nouveau nom de l'état/ la region
     */
    public void setEtatRegion(String etatRegion) {
        this.etatRegion = etatRegion;
    }

    /**
     * Récupère le nom de la ville du lieu
     * @return Nom de la ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Définit le nom de la ville du lieu
     * @param ville Nouvelle ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Récupère le libellé de l'adresse du lieu
     * @return libellé du lieu
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit le libellé de l'adresse du lieu
     * @param libelle Nouveau libellé
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Récupère le nom du pays
     * @return Nom du pays
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Définit le nom du pays
     * @param pays Nouveau nom
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * Récupère le lieu où le film est tourné
     * @return Nom du lieu
     */
    public List<Film> getLieuTournage() {
        return lieuTournage;
    }

    /**
     * Définit le lieu où le film est tourné
     * @param lieuTournage Nouveau lieu
     */
    public void setLieuTournage(List<Film> lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    /**
     * Récupère le lieu de Naissance des personnes
     * @return Nom du lieu
     */
    public List<Personne> getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * Définit le lieu de Naissance des personnes
     * @param lieuNaissance Nouveau lieu
     */
    public void setLieuNaissance(List<Personne> lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lieu lieu = (Lieu) o;
        return id != null && id.equals(lieu.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lieu{");
        sb.append("id=").append(id);
        sb.append(", etatRegion='").append(etatRegion).append('\'');
        sb.append(", ville='").append(ville).append('\'');
        sb.append(", libelle='").append(libelle).append('\'');
        sb.append(", pays=").append(pays != null ? pays.getNom() : "null");
        sb.append('}');
        return sb.toString();
    }
}
