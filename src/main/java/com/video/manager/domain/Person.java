package com.video.manager.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 40000)
    @Column(name = "biography", length = 40000)
    private String biography;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "deathday")
    private LocalDate deathday;

    @Size(max = 400)
    @Column(name = "homepage", length = 400)
    private String homepage;

    @OneToOne
    @JoinColumn(unique = true)
    private Picture profilePicture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public Person biography(String biography) {
        this.biography = biography;
        return this;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Person birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDeathday() {
        return deathday;
    }

    public Person deathday(LocalDate deathday) {
        this.deathday = deathday;
        return this;
    }

    public void setDeathday(LocalDate deathday) {
        this.deathday = deathday;
    }

    public String getHomepage() {
        return homepage;
    }

    public Person homepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public Person profilePicture(Picture picture) {
        this.profilePicture = picture;
        return this;
    }

    public void setProfilePicture(Picture picture) {
        this.profilePicture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        if (person.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", biography='" + biography + "'" +
            ", birthday='" + birthday + "'" +
            ", deathday='" + deathday + "'" +
            ", homepage='" + homepage + "'" +
            '}';
    }
}