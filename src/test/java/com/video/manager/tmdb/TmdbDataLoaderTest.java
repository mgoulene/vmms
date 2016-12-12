package com.video.manager.tmdb;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vagrant on 12/11/16.
 */
public class TmdbDataLoaderTest {
    private final Logger log = LoggerFactory.getLogger(TmdbDataLoaderTest.class);

    @Test
    public void testGetMoviesLoop() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            log.debug("Getting movie : "+i);
            TmdbDataLoader.the().getMovie(194);
        }
    }
}
