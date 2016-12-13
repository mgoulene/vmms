package com.video.manager.repository;

import com.video.manager.domain.Actor;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Actor entity.
 */
@SuppressWarnings("unused")
public interface ActorRepository extends JpaRepository<Actor,Long> {

}
