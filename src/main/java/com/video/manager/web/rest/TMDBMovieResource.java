package com.video.manager.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.video.manager.service.MovieService;
import com.video.manager.service.dto.MovieDTO;
import com.video.manager.web.rest.util.HeaderUtil;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TMDBMovieResource {
    private final Logger log = LoggerFactory.getLogger(TMDBMovieResource.class);
    private static String TMDB_KEY = "c344516cac0ae134d50ea9ed99e6a42c";
    @Inject
    private MovieService movieService;

    /*@GetMapping("/tmdb-movies")
    @Timed
    public List<MovieDb> getTMDBMovie()
        throws URISyntaxException {
        log.debug("REST request to get all Movie");
        TmdbSearch search = new TmdbApi("c344516cac0ae134d50ea9ed99e6a42c").getSearch();
        return search.searchMovie("Django", null, "fr", true, 1).getResults();
    }*/
    @GetMapping("/tmdb-movies/{id}")
    @Timed
    public ResponseEntity<MovieDb> getTMDBMovie(@PathVariable Long id) {
        log.debug("REST request to get TMDBMovie : {}", id);
        TmdbMovies movies = new TmdbApi(TMDB_KEY).getMovies();
        MovieDb movie = movies.getMovie(id.intValue(), "fr");
        return Optional.ofNullable(movie)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/_import/tmdb-movies/{id}")
    @Timed
    public ResponseEntity<MovieDTO> importTMDBMovie(@PathVariable Long id) throws URISyntaxException{
        log.debug("REST request to get TMDBMovie : {}", id);
        TmdbMovies tmdbMovies = new TmdbApi(TMDB_KEY).getMovies();
        MovieDb movieDb = tmdbMovies.getMovie(id.intValue(), "fr");
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(movieDb.getTitle());
        movieDTO.setOriginalTitle(movieDb.getOriginalTitle());
        movieDTO.setOverview(movieDb.getOverview());
        MovieDTO result = movieService.save(movieDTO);
        return ResponseEntity.created(new URI("/api/movies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("movie", result.getId().toString()))
            .body(result);
    }


    /**
     * SEARCH  /_search/movies?query=:query : search for the movie corresponding
     * to the query.
     *
     * @param query    the query of the movie search
     * @return the result of the search
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/_search/tmdb-movies")
    @Timed
    public List<MovieDb> searchMovies(@RequestParam String query)
        throws URISyntaxException {
        log.debug("REST request to search for a page of Movies for query {}", query);
        TmdbSearch search = new TmdbApi(TMDB_KEY).getSearch();
        return  search.searchMovie(query, null, "fr", true, 1).getResults();
    }

}
