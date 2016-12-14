package com.video.manager.repository.search;

import com.video.manager.domain.Crew;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Crew entity.
 */
public interface CrewSearchRepository extends ElasticsearchRepository<Crew, Long> {
}
