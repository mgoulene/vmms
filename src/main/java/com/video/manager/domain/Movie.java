package com.video.manager.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Movie.
 */
@Entity
@Table(name = "movie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Size(max = 200)
    @Column(name = "original_title", length = 200)
    private String originalTitle;

    @Column(name = "release_date")
    private String releaseDate;

    @Size(max = 40000)
    @Column(name = "overview", length = 40000)
    private String overview;

    @Size(max = 400)
    @Column(name = "homepage", length = 400)
    private String homepage;

    @Column(name = "budget")
    private Long budget;

    @Column(name = "revenue")
    private Long revenue;

    @Column(name = "runtime")
    private Integer runtime;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    @Column(name = "vote_rating")
    private Float voteRating;

    @Column(name = "vote_count")
    private Integer voteCount;

    @OneToOne
    @JoinColumn(unique = true)
    private Picture poster;

    @OneToOne
    @JoinColumn(unique = true)
    private Picture backdrop;

    @OneToOne
    @JoinColumn(unique = true)
    private Genre genre;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "movie_actor",
               joinColumns = @JoinColumn(name="movies_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="actors_id", referencedColumnName="ID"))
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "movie_artwork",
               joinColumns = @JoinColumn(name="movies_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="artworks_id", referencedColumnName="ID"))
    private Set<Picture> artworks = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Movie title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public Movie originalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Movie releaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public Movie overview(String overview) {
        this.overview = overview;
        return this;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getHomepage() {
        return homepage;
    }

    public Movie homepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Long getBudget() {
        return budget;
    }

    public Movie budget(Long budget) {
        this.budget = budget;
        return this;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Long getRevenue() {
        return revenue;
    }

    public Movie revenue(Long revenue) {
        this.revenue = revenue;
        return this;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public Movie runtime(Integer runtime) {
        this.runtime = runtime;
        return this;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Float getVoteRating() {
        return voteRating;
    }

    public Movie voteRating(Float voteRating) {
        this.voteRating = voteRating;
        return this;
    }

    public void setVoteRating(Float voteRating) {
        this.voteRating = voteRating;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Movie voteCount(Integer voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Picture getPoster() {
        return poster;
    }

    public Movie poster(Picture picture) {
        this.poster = picture;
        return this;
    }

    public void setPoster(Picture picture) {
        this.poster = picture;
    }

    public Picture getBackdrop() {
        return backdrop;
    }

    public Movie backdrop(Picture picture) {
        this.backdrop = picture;
        return this;
    }

    public void setBackdrop(Picture picture) {
        this.backdrop = picture;
    }

    public Genre getGenre() {
        return genre;
    }

    public Movie genre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public Movie actors(Set<Actor> actors) {
        this.actors = actors;
        return this;
    }

    public Movie addActor(Actor actor) {
        actors.add(actor);
        actor.getMovieActrors().add(this);
        return this;
    }

    public Movie removeActor(Actor actor) {
        actors.remove(actor);
        actor.getMovieActrors().remove(this);
        return this;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Picture> getArtworks() {
        return artworks;
    }

    public Movie artworks(Set<Picture> pictures) {
        this.artworks = pictures;
        return this;
    }

    public Movie addArtwork(Picture picture) {
        artworks.add(picture);
        return this;
    }

    public Movie removeArtwork(Picture picture) {
        artworks.remove(picture);
        return this;
    }

    public void setArtworks(Set<Picture> pictures) {
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
        Movie movie = (Movie) o;
        if (movie.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
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
