package com.video.manager.repository.search;

import com.video.manager.domain.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Movie entity.
 */
public interface MovieSearchRepository extends ElasticsearchRepository<Movie, Long> {
}
