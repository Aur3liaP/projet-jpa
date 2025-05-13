package fr.diginamic.dto;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) pour la représentation d'un Film.
 * Cette classe est utilisée pour faciliter la sérialisation/désérialisation
 * avec Jackson lors des échanges JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmDto {
    @JsonProperty("id")
    private String idImdb;

    @JsonProperty("nom")
    private String titre;

    private String url;

    private String rating;

    @JsonProperty("plot")
    private String resume;

    private String langue;

    @JsonProperty("anneeSortie")
    private String annee;

    private PaysDto pays;

    @JsonProperty("lieuTournage")
    private LieuDto lieuTournage;

    private List<RealisateurDto> realisateurs = new ArrayList<>();

    @JsonProperty("castingPrincipal")
    private List<ActeurDto> acteursPrincipaux = new ArrayList<>();

    private List<RoleDto> roles = new ArrayList<>();

    @JsonProperty("genres")
    private List<String> genres = new ArrayList<>();

    /**
     * Constructeur par défaut
     */
    public FilmDto() {
    }

    /**
     * Constructeur complet de la classe FilmDto.
     *
     * @param idImdb            Identifiant unique du film sur IMDb.
     * @param titre             Titre du film tel qu'affiché dans la fiche.
     * @param url               URL IMDb du film.
     * @param rating            Note du film (souvent sur 10).
     * @param resume            Résumé ou synopsis du film.
     * @param langue            Langue principale du film.
     * @param annee             Année de sortie du film.
     * @param pays              Pays d'origine du film (objet DTO).
     * @param lieuTournage      Lieu de tournage principal du film (objet DTO).
     * @param realisateurs      Liste des réalisateurs du film (objets DTO).
     * @param acteursPrincipaux Liste des acteurs principaux du film (objets DTO).
     * @param roles             Liste des rôles présents dans le film (objets DTO).
     * @param genres            Liste des genres auxquels le film appartient (objets DTO).
     */

    public FilmDto(String idImdb, String titre, String url, String rating, String resume, String langue, String annee, PaysDto pays, LieuDto lieuTournage, List<RealisateurDto> realisateurs, List<ActeurDto> acteursPrincipaux, List<RoleDto> roles, List<String> genres) {
        this.idImdb = idImdb;
        this.titre = titre;
        this.url = url;
        this.rating = rating;
        this.resume = resume;
        this.langue = langue;
        this.annee = annee;
        this.pays = pays;
        this.lieuTournage = lieuTournage;
        this.realisateurs = realisateurs;
        this.acteursPrincipaux = acteursPrincipaux;
        this.roles = roles;
        this.genres = genres;
    }

    /**
     * Getters et Setters
     */

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public PaysDto getPays() {
        return pays;
    }

    public void setPays(PaysDto pays) {
        this.pays = pays;
    }

    public LieuDto getLieuTournage() {
        return lieuTournage;
    }

    public void setLieuTournage(LieuDto lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    public List<RealisateurDto> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(List<RealisateurDto> realisateurs) {
        this.realisateurs = realisateurs;
    }

    public List<ActeurDto> getActeursPrincipaux() {
        return acteursPrincipaux;
    }

    public void setActeursPrincipaux(List<ActeurDto> acteursPrincipaux) {
        this.acteursPrincipaux = acteursPrincipaux;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FilmDto{");
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
        sb.append(", acteursPrincipaux=").append(acteursPrincipaux);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
