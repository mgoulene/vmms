package com.video.manager.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
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

    @Size(max = 40000)
    private String overview;


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
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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
            ", overview='" + overview + "'" +
            '}';
    }
}
