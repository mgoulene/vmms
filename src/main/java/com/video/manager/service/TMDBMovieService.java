package com.video.manager.service;

import com.video.manager.domain.Movie;
import com.video.manager.service.dto.MovieDTO;
import com.video.manager.tmdb.TmdbDataLoader;
import info.movito.themoviedbapi.model.MovieDb;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by vagrant on 12/14/16.
 */
public interface TMDBMovieService {
    public MovieDb findOne(int tmdbId);
    public List<MovieDb> searchTMDBMovies(String query);
    public MovieDTO saveMovie(int tmbdId);

}
