package com.video.manager.repository;

import com.video.manager.domain.Crew;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Crew entity.
 */
@SuppressWarnings("unused")
public interface CrewRepository extends JpaRepository<Crew,Long> {

}
