package com.video.manager.service.impl;

import com.video.manager.service.CrewService;
import com.video.manager.domain.Crew;
import com.video.manager.repository.CrewRepository;
import com.video.manager.repository.search.CrewSearchRepository;
import com.video.manager.service.dto.CrewDTO;
import com.video.manager.service.mapper.CrewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Crew.
 */
@Service
@Transactional
public class CrewServiceImpl implements CrewService{

    private final Logger log = LoggerFactory.getLogger(CrewServiceImpl.class);
    
    @Inject
    private CrewRepository crewRepository;

    @Inject
    private CrewMapper crewMapper;

    @Inject
    private CrewSearchRepository crewSearchRepository;

    /**
     * Save a crew.
     *
     * @param crewDTO the entity to save
     * @return the persisted entity
     */
    public CrewDTO save(CrewDTO crewDTO) {
        log.debug("Request to save Crew : {}", crewDTO);
        Crew crew = crewMapper.crewDTOToCrew(crewDTO);
        crew = crewRepository.save(crew);
        CrewDTO result = crewMapper.crewToCrewDTO(crew);
        crewSearchRepository.save(crew);
        return result;
    }

    /**
     *  Get all the crews.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<CrewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Crews");
        Page<Crew> result = crewRepository.findAll(pageable);
        return result.map(crew -> crewMapper.crewToCrewDTO(crew));
    }

    /**
     *  Get one crew by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public CrewDTO findOne(Long id) {
        log.debug("Request to get Crew : {}", id);
        Crew crew = crewRepository.findOne(id);
        CrewDTO crewDTO = crewMapper.crewToCrewDTO(crew);
        return crewDTO;
    }

    /**
     *  Delete the  crew by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Crew : {}", id);
        crewRepository.delete(id);
        crewSearchRepository.delete(id);
    }

    /**
     * Search for the crew corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CrewDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Crews for query {}", query);
        Page<Crew> result = crewSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(crew -> crewMapper.crewToCrewDTO(crew));
    }
}
