package com.video.manager.repository.search;

import com.video.manager.domain.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Person entity.
 */
public interface PersonSearchRepository extends ElasticsearchRepository<Person, Long> {
}
