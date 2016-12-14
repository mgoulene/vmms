package com.video.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Crew.
 */
@Entity
@Table(name = "crew")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "crew")
public class Crew implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "department")
    private String department;

    @Column(name = "job")
    private String job;

    @OneToOne
    @JoinColumn(unique = true)
    private Person person;

    @ManyToMany(mappedBy = "crews")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Movie> movieCrews = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public Crew department(String department) {
        this.department = department;
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public Crew job(String job) {
        this.job = job;
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Person getPerson() {
        return person;
    }

    public Crew person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Movie> getMovieCrews() {
        return movieCrews;
    }

    public Crew movieCrews(Set<Movie> movies) {
        this.movieCrews = movies;
        return this;
    }

    public Crew addMovieCrew(Movie movie) {
        movieCrews.add(movie);
        movie.getCrews().add(this);
        return this;
    }

    public Crew removeMovieCrew(Movie movie) {
        movieCrews.remove(movie);
        movie.getCrews().remove(this);
        return this;
    }

    public void setMovieCrews(Set<Movie> movies) {
        this.movieCrews = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Crew crew = (Crew) o;
        if (crew.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, crew.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Crew{" +
            "id=" + id +
            ", department='" + department + "'" +
            ", job='" + job + "'" +
            '}';
    }
}
