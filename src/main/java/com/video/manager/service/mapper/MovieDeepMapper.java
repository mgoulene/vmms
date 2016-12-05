package com.video.manager.service.mapper;

import com.video.manager.domain.Movie;
import com.video.manager.domain.Picture;
import com.video.manager.service.dto.MovieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for the entity Movie and its DTO MovieDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MovieDeepMapper {

    @Mapping(source = "poster.id", target = "posterId")
    @Mapping(source = "poster", target = "poster")
    MovieDTO movieToMovieDTO(Movie movie);

}
