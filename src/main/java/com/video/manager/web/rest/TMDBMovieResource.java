package com.video.manager.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.video.manager.domain.enumeration.PictureType;
import com.video.manager.service.MovieService;
import com.video.manager.service.PictureService;
import com.video.manager.service.dto.MovieDTO;
import com.video.manager.service.dto.PictureDTO;
import com.video.manager.web.rest.util.HeaderUtil;
import com.video.manager.web.utils.GetPictureFromURL;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.Utils;
import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCredit;
import info.movito.themoviedbapi.model.people.PersonCrew;
import info.movito.themoviedbapi.model.people.PersonPeople;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TMDBMovieResource {
    private final Logger log = LoggerFactory.getLogger(TMDBMovieResource.class);
    private static String TMDB_KEY = "c344516cac0ae134d50ea9ed99e6a42c";
    @Inject
    private MovieService movieService;
    @Inject
    private PictureService pictureService;

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
        TmdbApi tmdbApi = new TmdbApi(TMDB_KEY);
        TmdbMovies tmdbMovies = tmdbApi.getMovies();
        MovieDb movieDb = tmdbMovies.getMovie(id.intValue(), "fr");
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(movieDb.getTitle());
        List<PersonCast> casts =  movieDb.getCast();
        List<PersonCrew> crew = movieDb.getCrew();
        Credits credits = tmdbMovies.getCredits(id.intValue());
        for (int i=0; i<credits.getCast().size(); i++) {
            //PersonPeople person = tmdbApi.getPeople().getPersonInfo(credits.getCast().get(i).getId());
            //System.out.println(person.getCharacter());
        }
        MovieImages movieImages = tmdbMovies.getImages(movieDb.getId(),"fr");
        byte[] posterData = GetPictureFromURL.getBytes(tmdbApi, movieDb.getPosterPath());
        PictureDTO poster = new PictureDTO();
        poster.setImage(posterData);
        poster.setType(PictureType.POSTER_MOVIE);
        poster.setImageContentType(MimeTypeUtils.IMAGE_JPEG.getType());
        System.out.println(".Creating Poster for : "+movieDb.getTitle());
        poster = pictureService.save(poster);
        movieDTO.setPosterId(poster.getId());
        for (int i = 0 ; i < movieImages.getPosters().size(); i++) {
            String artworkURL = movieImages.getPosters().get(i).getFilePath();
            byte[] artworkBytes = GetPictureFromURL.getBytes(tmdbApi, artworkURL);
            PictureDTO artworkPictureDTO = new PictureDTO();
            artworkPictureDTO.setType(PictureType.ARTWORK);
            artworkPictureDTO.setImageContentType(MimeTypeUtils.IMAGE_JPEG.getType());
            artworkPictureDTO.setImage(artworkBytes);
            System.out.println("..Creating Artwork for : "+movieDb.getTitle());
            artworkPictureDTO = pictureService.save(artworkPictureDTO);
            movieDTO.getArtworks().add(artworkPictureDTO);
        }
        movieDTO.setOriginalTitle(movieDb.getOriginalTitle());
        movieDTO.setOverview(movieDb.getOverview());
        System.out.println("Creating Movie : "+movieDb.getTitle());
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
