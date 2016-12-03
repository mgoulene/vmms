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

    MovieDTO movieToMovieDTO(Movie movie);

    List<MovieDTO> moviesToMovieDTOs(List<Movie> movies);

    Movie movieDTOToMovie(MovieDTO movieDTO);

    List<Movie> movieDTOsToMovies(List<MovieDTO> movieDTOs);
}
