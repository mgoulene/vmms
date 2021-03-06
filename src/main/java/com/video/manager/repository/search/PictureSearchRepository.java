package com.video.manager.repository.search;

import com.video.manager.domain.Picture;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Picture entity.
 */
public interface PictureSearchRepository extends ElasticsearchRepository<Picture, Long> {
}
