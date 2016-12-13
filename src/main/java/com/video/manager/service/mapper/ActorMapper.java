package com.video.manager.service.mapper;

import com.video.manager.domain.*;
import com.video.manager.service.dto.ActorDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Actor and its DTO ActorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActorMapper {

    @Mapping(source = "person.id", target = "personId")
    ActorDTO actorToActorDTO(Actor actor);

    List<ActorDTO> actorsToActorDTOs(List<Actor> actors);

    @Mapping(source = "personId", target = "person")
    @Mapping(target = "movieActrors", ignore = true)
    Actor actorDTOToActor(ActorDTO actorDTO);

    List<Actor> actorDTOsToActors(List<ActorDTO> actorDTOs);

    default Person personFromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
