package com.video.manager.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Movie entity.
 */
public class MovieDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 200)
    private String title;

    @Size(max = 200)
    private String originalTitle;

    private String releaseDate;

    @Size(max = 40000)
    private String overview;

    @Size(max = 400)
    private String homepage;

    private Long budget;

    private Long revenue;

    private Integer runtime;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    private Float voteRating;

    private Integer voteCount;


    private Long posterId;
    
    private Long backdropId;
    
    private Long genreId;
    
    private Set<ActorDTO> actors = new HashSet<>();

    private Set<PictureDTO> artworks = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }
    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }
    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }
    public Float getVoteRating() {
        return voteRating;
    }

    public void setVoteRating(Float voteRating) {
        this.voteRating = voteRating;
    }
    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Long getPosterId() {
        return posterId;
    }

    public void setPosterId(Long pictureId) {
        this.posterId = pictureId;
    }

    public Long getBackdropId() {
        return backdropId;
    }

    public void setBackdropId(Long pictureId) {
        this.backdropId = pictureId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public Set<ActorDTO> getActors() {
        return actors;
    }

    public void setActors(Set<ActorDTO> actors) {
        this.actors = actors;
    }

    public Set<PictureDTO> getArtworks() {
        return artworks;
    }

    public void setArtworks(Set<PictureDTO> pictures) {
        this.artworks = pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MovieDTO movieDTO = (MovieDTO) o;

        if ( ! Objects.equals(id, movieDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", originalTitle='" + originalTitle + "'" +
            ", releaseDate='" + releaseDate + "'" +
            ", overview='" + overview + "'" +
            ", homepage='" + homepage + "'" +
            ", budget='" + budget + "'" +
            ", revenue='" + revenue + "'" +
            ", runtime='" + runtime + "'" +
            ", voteRating='" + voteRating + "'" +
            ", voteCount='" + voteCount + "'" +
            '}';
    }
}
