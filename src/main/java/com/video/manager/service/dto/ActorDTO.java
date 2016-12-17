package com.video.manager.service.dto;

import com.video.manager.domain.Person;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Actor entity.
 */
public class ActorDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer actorOrder;

    private String actorCharacter;


    private Long personId;

    private String personName;

    private Long personProfilePictureId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getActorOrder() {
        return actorOrder;
    }

    public void setActorOrder(Integer actorOrder) {
        this.actorOrder = actorOrder;
    }
    public String getActorCharacter() {
        return actorCharacter;
    }

    public void setActorCharacter(String actorCharacter) {
        this.actorCharacter = actorCharacter;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActorDTO actorDTO = (ActorDTO) o;

        if ( ! Objects.equals(id, actorDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ActorDTO{" +
            "id=" + id +
            ", actorOrder='" + actorOrder + "'" +
            ", actorCharacter='" + actorCharacter + "'" +
            '}';
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getPersonProfilePictureId() {
        return personProfilePictureId;
    }

    public void setPersonProfilePictureId(Long personProfilePictureId) {
        this.personProfilePictureId = personProfilePictureId;
    }
}
