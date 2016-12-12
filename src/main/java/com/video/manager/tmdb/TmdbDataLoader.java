package com.video.manager.tmdb;

import com.video.manager.util.LimitedCallPerPeriodUtil;
import com.video.manager.web.rest.TMDBMovieResource;
import com.video.manager.web.utils.GetPictureFromURL;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.model.people.PersonPeople;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by vagrant on 12/11/16.
 */
public class TmdbDataLoader {

    private final Logger log = LoggerFactory.getLogger(TmdbDataLoader.class);

    private static String TMDB_KEY = "c344516cac0ae134d50ea9ed99e6a42c";
    private static String LANGAGE = "fr";

    private static TmdbDataLoader _the;

    private TmdbApi api;
    private TmdbMovies movies;
    private TmdbPeople people;
    private TmdbSearch search;
    private LimitedCallPerPeriodUtil tmbdCalls;

    public static synchronized TmdbDataLoader the() {
        if (_the == null) {
            _the = new TmdbDataLoader();
        }
        return _the;
    }

    private TmdbDataLoader() {
        api = new TmdbApi(TMDB_KEY);
        movies = api.getMovies();
        people = api.getPeople();
        search = api.getSearch();
        tmbdCalls = new LimitedCallPerPeriodUtil(10000,35);
    }

    public MovieDb getMovie(int movieId) {
        tmbdCalls.waitForCall();
        return movies.getMovie(movieId, LANGAGE);
    }

    public Credits getCredits(int movieId)  {
        tmbdCalls.waitForCall();
        return       movies.getCredits(movieId);
    }

    public MovieImages getImages(int movieId) {
        tmbdCalls.waitForCall();
        return movies.getImages(movieId, LANGAGE);
    }
    public byte[] getImageData(String path) {
        tmbdCalls.waitForCall();
        return GetPictureFromURL.getBytes(api, path);
    }

    public PersonPeople getPersonInfo(int personId) {
        tmbdCalls.waitForCall();
        return people.getPersonInfo(personId);
    }

    public List<MovieDb> searchMovie(String query) {
        return search.searchMovie(query, null, LANGAGE, false, 1).getResults();
    }

}
