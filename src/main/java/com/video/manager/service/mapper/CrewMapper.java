package com.video.manager.service.mapper;

import com.video.manager.domain.*;
import com.video.manager.service.dto.CrewDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Crew and its DTO CrewDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CrewMapper {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.name", target = "personName")
    @Mapping(source = "person.profilePicture.id", target="personProfilePictureId")
    CrewDTO crewToCrewDTO(Crew crew);

    List<CrewDTO> crewsToCrewDTOs(List<Crew> crews);

    @Mapping(source = "personId", target = "person")
    @Mapping(target = "movieCrews", ignore = true)
    Crew crewDTOToCrew(CrewDTO crewDTO);

    List<Crew> crewDTOsToCrews(List<CrewDTO> crewDTOs);

    default Person personFromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
