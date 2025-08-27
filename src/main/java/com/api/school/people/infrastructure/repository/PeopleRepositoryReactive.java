package com.api.school.people.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.api.school.people.infrastructure.entity.PeopleEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * PeopleRepositoryReactive.
 *
 * @author Joseph Magallanes
 * @since 2025-08-26
 */
@Repository
public interface PeopleRepositoryReactive extends ReactiveCrudRepository<PeopleEntity, Integer> {
    Mono<PeopleEntity> findByDocumentNumber(String documentNumber);
    Flux<PeopleEntity> findByFirstNameContainingIgnoreCase(String name);
    Flux<PeopleEntity> findByLastNameContainingIgnoreCase(String LastName);
}
