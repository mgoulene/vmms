package com.video.manager.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Crew entity.
 */
public class CrewDTO implements Serializable {

    private Long id;

    private String department;

    private String job;

    private String personName;

    private Long personProfilePictureId;


    private Long personId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

        CrewDTO crewDTO = (CrewDTO) o;

        if ( ! Objects.equals(id, crewDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CrewDTO{" +
            "id=" + id +
            ", department='" + department + "'" +
            ", job='" + job + "'" +
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
