package com.video.manager.service.mapper;

import com.video.manager.domain.*;
import com.video.manager.service.dto.MovieDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Movie and its DTO MovieDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MovieMapper {



    @Mapping(source = "poster.id", target = "posterId")
    MovieDTO movieToMovieDTO(Movie movie);

    List<MovieDTO> moviesToMovieDTOs(List<Movie> movies);

    @Mapping(source = "posterId", target = "poster")
    Movie movieDTOToMovie(MovieDTO movieDTO);

    List<Movie> movieDTOsToMovies(List<MovieDTO> movieDTOs);

    default Picture pictureFromId(Long id) {
        if (id == null) {
            return null;
        }
        Picture picture = new Picture();
        picture.setId(id);
        return picture;
    }
}
