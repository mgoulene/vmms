package com.video.manager.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.video.manager.domain.enumeration.PictureType;
import com.video.manager.service.MovieService;
import com.video.manager.service.PictureService;
import com.video.manager.service.TMDBMovieService;
import com.video.manager.service.dto.MovieDTO;
import com.video.manager.service.dto.PictureDTO;
import com.video.manager.tmdb.TmdbDataLoader;
import com.video.manager.web.rest.util.HeaderUtil;
import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.model.people.PersonPeople;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TMDBMovieResource {
    private static String TMDB_KEY = "c344516cac0ae134d50ea9ed99e6a42c";
    private final Logger log = LoggerFactory.getLogger(TMDBMovieResource.class);
    @Inject
    private MovieService movieService;
    @Inject
    private PictureService pictureService;
    @Inject
    private TMDBMovieService tmdbMovieService;

    @GetMapping("/tmdb-movies/{id}")
    @Timed
    public ResponseEntity<MovieDb> getTMDBMovie(@PathVariable Long id) {
        log.debug("REST request to get TMDBMovie : {}", id);
        MovieDb movieDb = tmdbMovieService.findOne(id.intValue());
        return Optional.ofNullable(movieDb)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/_import/tmdb-movies/{id}")
    @Timed
    public ResponseEntity<MovieDTO> importTMDBMovie(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to import TMDBMovie : {}", id);
        MovieDTO createdMovieDTO = tmdbMovieService.saveMovie(id.intValue());
        return ResponseEntity.created(new URI("/api/movies/" + createdMovieDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("movie", createdMovieDTO.getId().toString()))
            .body(createdMovieDTO);

    }


    /**
     * SEARCH  /_search/movies?query=:query : search for the movie corresponding
     * to the query.
     *
     * @param query the query of the movie search
     * @return the result of the search
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/_search/tmdb-movies")
    @Timed
    public List<MovieDb> searchMovies(@RequestParam String query)
        throws URISyntaxException {
        log.debug("REST request to search for a page of Movies for query {}", query);
        List<MovieDb> movieDbs = tmdbMovieService.searchTMDBMovies(query);
        return movieDbs;
    }

}
