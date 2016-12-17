package com.video.manager.repository;

import com.video.manager.domain.Movie;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Movie entity.
 */
@SuppressWarnings("unused")
public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("select distinct movie from Movie movie left join fetch movie.actors left join fetch movie.crews left join fetch movie.artworks left join fetch movie.genres")
    List<Movie> findAllWithEagerRelationships();

    // TODO : select movie from Movie movie left join fetch movie.actors actor left join fetch actor.person left join fetch movie.crews left join fetch movie.artworks left join fetch movie.genres where movie.id =:id
    @Query("select movie from Movie movie left join fetch movie.actors movieActor left join fetch movieActor.person left join fetch movie.crews movieCrew left join fetch movieCrew.person left join fetch movie.genres where movie.id =:id")
    Movie findOneWithEagerRelationships(@Param("id") Long id);
    Movie findOneByTmdbId(int tmbdId);

}
