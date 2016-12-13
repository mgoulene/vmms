package com.video.manager.repository.search;

import com.video.manager.domain.Actor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Actor entity.
 */
public interface ActorSearchRepository extends ElasticsearchRepository<Actor, Long> {
}
