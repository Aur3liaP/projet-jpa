package fr.diginamic.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;


/**
 * Entité représentant un film dans la base de données.
 * Cette classe est mappée à la table "Films" et contient les informations
 * essentielles sur un film comme son titre, son année de sortie, sa note, etc.
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Films", indexes = {
        @Index(name = "idx_id_imdb", columnList = "ID_IMDB"),
        @Index(name = "idx_titre", columnList = "TITRE"),
})
public class Film {

    /** Identifiant unique du film provenant de IMDb */
    @Id
    @Column(name = "ID_IMDB", length = 50)
    private String idImdb;

    /** Titre du film */
    @Column(name = "TITRE", length = 50, nullable = false)
    private String titre;

    /** Année de sortie du film */
    @Column(name = "ANNEE")
    private String annee;

    /** Note du film (sur 10) */
    @Column(name = "RATING")
    private Float rating;

    /** URL vers la page du film */
    @Column(name = "URL")
    private String url;

    /** Langue principale du film */
    @Column(name = "LANGUE", length = 50)
    private String langue;

    /** Résumé ou synopsis du film */
    @Column(name = "RESUME", length = 500)
    private String resume;

    /** Pays d'origine du film */
    @ManyToOne
    @JoinColumn(name = "id_Pays")
    private Pays pays;

    /** Lieu principal de tournage */
    @ManyToOne
    @JoinColumn(name = "id_Lieu_Tournage")
    private Lieu lieuTournage;

    /** Relation many-to-many avec les genres */
    @ManyToMany
    @JoinTable(name = "Films_Genres",
            joinColumns = @JoinColumn(name = "ID_FILM"),
            inverseJoinColumns = @JoinColumn(name = "ID_GENRE"))
    private List<Genre> genres = new ArrayList<>();

    /** Association many-to-many avec les réalisateurs */
    @ManyToMany
    @JoinTable(
            name = "Films_Realisateurs",
            joinColumns = @JoinColumn(name = "ID_FILM"),
            inverseJoinColumns = @JoinColumn(name = "ID_REALISATEUR")
    )
    private List<Realisateur> realisateurs = new ArrayList<>();

    /** Association many-to-many avec le casting principal */
    @ManyToMany
    @JoinTable(
            name = "CastingPrincipal",
            joinColumns = @JoinColumn(name = "ID_FILM"),
            inverseJoinColumns = @JoinColumn(name = "ID_ACTEUR")
    )
    private List<Acteur> acteurs = new ArrayList<>();

    /** Association avec les rôles secondaires */
    @OneToMany(mappedBy = "film")
    private List<Role> roles = new ArrayList<>();

    /**
     * Constructeur par défaut
     */
    public Film() {
    }

    /**
     * Constructeur avec paramètres essentiels
     *
     * @param idImdb identifiant unique IMDb
     * @param titre titre du film
     */
    public Film(String idImdb, String titre) {
        this.idImdb = idImdb;
        this.titre = titre;
    }

    /**
     * Ajoute un genre au film
     *
     * @param genre le genre à ajouter
     * @return this pour chaînage
     */
    public Film ajouterGenre(Genre genre) {
        this.genres.add(genre);
        return this;
    }

    /**
     * Ajoute un réalisateur au film
     *
     * @param realisateur le réalisateur à associer
     * @return l'association créée
     */
    public Film ajouterRealisateur(Realisateur realisateur) {
        this.realisateurs.add(realisateur);
        return this;
    }

    /**
     * Ajoute un acteur au casting principal
     *
     * @param acteur l'acteur à ajouter au casting
     * @return l'association créée
     */
    public Film ajouterActeurPrincipal(Acteur acteur) {
        this.acteurs.add(acteur);
        return this;
    }

    /**
     * Ajoute le rôle d'un acteur au film
     *
     * @param acteur l'acteur jouant le rôle
     * @param nomPersonnage le nom du personnage joué
     * @return l'association créée
     */
    public Role ajouterRole(Acteur acteur, String nomPersonnage) {
        Role role = new Role(this, acteur, nomPersonnage);
        this.roles.add(role);
        acteur.getRoles().add(role);
        return role;
    }

    public String getIdImdb() {
        return idImdb;
    }

    public void setIdImdb(String idImdb) {
        this.idImdb = idImdb;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Lieu getLieuTournage() {
        return lieuTournage;
    }

    public void setLieuTournage(Lieu lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(List<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    public List<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     *
     * Getters et Setters
     */



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return idImdb != null && idImdb.equals(film.idImdb);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Film{");
        sb.append("idImdb='").append(idImdb).append('\'');
        sb.append(", titre='").append(titre).append('\'');
        sb.append(", annee=").append(annee);
        sb.append(", rating=").append(rating);
        sb.append(", url='").append(url).append('\'');
        sb.append(", langue='").append(langue).append('\'');
        sb.append(", resume='").append(resume).append('\'');
        sb.append(", pays=").append(pays);
        sb.append(", lieuTournage=").append(lieuTournage);
        sb.append(", genres=").append(genres);
        sb.append(", realisateurs=").append(realisateurs);
        sb.append(", acteurs=").append(acteurs);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
